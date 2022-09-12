package jp.co.ginga.web.application.controller.facility.confirm;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ginga.web.application.controller.facility.add.FacilityAddForm;
import jp.co.ginga.web.application.controller.facility.detail.FacilityDetailForm;
import jp.co.ginga.web.application.util.session.facility.FacilitySession;
import jp.co.ginga.web.domain.service.facility.FacilityManagerDto;
import jp.co.ginga.web.domain.service.facility.FacilityManagerService;
import jp.co.ginga.web.domain.service.facilityType.FacilityTypeManagerService;
import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;
import lombok.Data;

@Data
@Controller
public class FacilityConfirmController {
	@Autowired
	MessageSource msg;

	@Autowired
	FacilitySession facilitySession;

	@Autowired
	FacilityConfirmHelper facilityConfirmHelper;

	@Autowired
	FacilityManagerService facilityManagerService;

	@Autowired
	FacilityTypeManagerService facilityTypeManagerService;


	@PostMapping(path="/facility/confirm", params="add")
	public String postFacilityConfirmAdd(@ModelAttribute @Validated FacilityAddForm inputForm, BindingResult result, RedirectAttributes ra, Model model) {
		boolean flag = false;

		this.facilitySession.setFacilityForm(inputForm.getFacilityForm());

		if(result.hasErrors()) {
			flag = true;
		}

		FacilityManagerDto dto = this.facilityManagerService.getFacilityByName(inputForm.getFacilityForm().getName());

		if(dto.getFacilityDtoList().size() != 0) {
			result.rejectValue("facilityForm.name", "Facility.duplicate");
			flag = true;
		}

		FacilityConfirmForm facilityConfirmForm = this.facilityConfirmHelper.createFacilityConfirmForm(this.facilitySession);

		facilityConfirmForm.setBtnName(this.msg.getMessage("label.add", null, Locale.JAPAN ));
		facilityConfirmForm.setBtnAction(this.msg.getMessage("action.add", null, Locale.JAPAN ));

		if(flag) {
			ra.addFlashAttribute("errors", result);
			return "redirect:/facility/add";
		}
		model.addAttribute("facilityConfirmForm", facilityConfirmForm);
		return "facility/facility-confirm";
	}

	@PostMapping(path="/facility/confirm", params="update")
	public String postFacilityConfirmUpdate(@ModelAttribute @Validated FacilityDetailForm inputForm, BindingResult result, RedirectAttributes ra, Model model) {
		boolean errorFlag =false;
		boolean duplicationFlag = false;

		if(result.hasErrors()) {
			errorFlag = true;
		}

//		final int NAME_UPDATE = this.facilityConfirmHelper.NAME_UPDATE;
//		final int NAME_AND_FACILITY_TYPE_UPDATE = this.facilityConfirmHelper.NAME_UPDATE + this.facilityConfirmHelper.FACILITY_TYPE_UPDATE;
//		final int NAME_AND_CAPACITY_UPDATE = this.facilityConfirmHelper.NAME_UPDATE + this.facilityConfirmHelper.CAPACITY_UPDATE;
//		final int ALL_UPDATE = this.facilityConfirmHelper.NAME_UPDATE + this.facilityConfirmHelper.FACILITY_TYPE_UPDATE + this.facilityConfirmHelper.CAPACITY_UPDATE;
//		final int NO_UPDATE = this.facilityConfirmHelper.NO_UPDATE;
//		int this.facilityConfirmHelper.checkUpdated(inputForm, this.facilitySession.getBeforeFacilityForm());

		switch(this.facilityConfirmHelper.checkUpdated(inputForm, this.facilitySession.getBeforeFacilityForm())) {
			case 1:
			case 11:
			case 101:
			case 111:
				duplicationFlag = true;
				break;
			case 0:
				result.rejectValue("sysMsg", null, this.msg.getMessage("Facility.notChange", null, Locale.JAPAN));
				errorFlag = true;
			default:
				break;
		}

		if(duplicationFlag) {
			FacilityManagerDto facilityManagerDto = this.facilityManagerService.getFacilityByName(inputForm.getFacilityForm().getName()	);
			if(facilityManagerDto.getFacilityDtoList().size() > 0) {
				result.rejectValue("sysMsg", null, this.msg.getMessage("Facility.duplicate", null, Locale.JAPAN));
				errorFlag=true;
			}
		}



		if(errorFlag) {
			ra.addFlashAttribute("errors", result);
			return "redirect:/facility/detail/"+this.facilitySession.getFacilityForm().getFacilityId();
		}

		//先にsessionに保存してしまうと更新を二度おすすと修正内容が保持されてしまい、同じ名前の施設が登録できてしまう
		this.facilitySession.getFacilityForm().setName(inputForm.getFacilityForm().getName());
		this.facilitySession.getFacilityForm().setCapacity(inputForm.getFacilityForm().getCapacity());
		this.facilitySession.getFacilityForm().getFacilityTypeForm().setFacilityTypeId(inputForm.getFacilityForm().getFacilityTypeForm().getFacilityTypeId());
		FacilityConfirmForm facilityConfirmForm = this.facilityConfirmHelper.createFacilityConfirmForm(this.facilitySession);
		facilityConfirmForm.setBtnName(this.msg.getMessage("label.update", null, Locale.JAPAN ));
		facilityConfirmForm.setBtnAction(this.msg.getMessage("action.update", null, Locale.JAPAN ));

		model.addAttribute("facilityConfirmForm", facilityConfirmForm);

		return "/facility/facility-confirm";
	}

	@PostMapping(path="/facility/confirm", params="delete")
	public String postFacilityConfirmDelete(@ModelAttribute @Validated FacilityDetailForm inputForm, BindingResult result, RedirectAttributes ra, Model model) {
		FacilityManagerDto facilityManagerDto = this.facilityManagerService.getFacilityDetailByFacilityId(this.facilitySession.getFacilityForm().getFacilityId());
		FacilityTypeDto facilityTypeDto = this.facilityTypeManagerService.getFacilityTypeDto(this.facilitySession.getFacilityForm().getFacilityTypeForm().getFacilityTypeId());
		facilityManagerDto.setFacilityTypeDto(facilityTypeDto);

		boolean errorFlag = false;

		if(this.facilityConfirmHelper.checkUpdated(facilityManagerDto, this.facilitySession.getBeforeFacilityForm()) != this.facilityConfirmHelper.NO_UPDATE) {
			result.rejectValue("sysMsg", null, this.msg.getMessage("Facility.optimistic", null, Locale.JAPAN));
			errorFlag = true;
		}

		if(errorFlag) {
			ra.addFlashAttribute("errors", result);
			return "redirect:/facility/detail/" + this.facilitySession.getFacilityForm().getFacilityId();
		}

		FacilityConfirmForm facilityConfirmForm = this.facilityConfirmHelper.createFacilityConfirmForm(facilityManagerDto);
		facilityConfirmForm.setBtnName(this.msg.getMessage("label.delete", null, Locale.JAPAN ));
		facilityConfirmForm.setBtnAction(this.msg.getMessage("action.delete", null, Locale.JAPAN ));

		this.facilitySession.getFacilityForm().setName(facilityConfirmForm.getFacilityForm().getName());
		this.facilitySession.getFacilityForm().setCapacity(facilityConfirmForm.getFacilityForm().getCapacity());
		this.facilitySession.getFacilityForm().getFacilityTypeForm().setFacilityTypeId(facilityConfirmForm.getFacilityForm().getFacilityTypeForm().getFacilityTypeId());

		model.addAttribute("facilityConfirmForm", facilityConfirmForm);

		return "facility/facility-confirm";
	}
}
