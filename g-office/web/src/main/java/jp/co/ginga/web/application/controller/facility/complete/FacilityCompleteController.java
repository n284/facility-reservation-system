package jp.co.ginga.web.application.controller.facility.complete;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ginga.web.application.util.session.facility.FacilitySession;
import jp.co.ginga.web.domain.service.facility.FacilityManagerDto;
import jp.co.ginga.web.domain.service.facility.FacilityManagerService;
import jp.co.ginga.web.domain.service.facilityType.FacilityTypeManagerService;
import jp.co.ginga.web.domain.service.util.constant.ServiceConst;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;
import jp.co.ginga.web.util.security.domain.service.security.UserDetailsImpl;

@Controller
public class FacilityCompleteController {

	@Autowired
	MessageSource msg;

	@Autowired
	FacilitySession facilitySession;

	@Autowired
	FacilityCompleteHelper facilityCompleteHelper;

	@Autowired
	FacilityManagerService facilityManagerService;

	@Autowired
	FacilityTypeManagerService facilityTypeManagerService;

	@GetMapping(path="/facility/complete")
	public String createFacilityComplete(ModelMap modelMap) {
		FacilityCompleteForm facilityCompleteForm = FacilityCompleteForm.getInstance();
		if(modelMap.get("data") == null) {
			return "/error/error";
		}

		if(modelMap.containsKey("data")){
			modelMap.addAttribute("facilityComplete", facilityCompleteForm);
		}else {
			facilityCompleteForm.setSysMsg(this.msg.getMessage("Already.completed",null, Locale.JAPAN));
			modelMap.addAttribute("facilityComplete", facilityCompleteForm);
		}


		return "facility/facility-complete";
	}

	@PostMapping(path="/facility/complete", params = "add")
	public String createFacilityCompleteAdd(ModelMap modelMap, RedirectAttributes ra, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		FacilityCompleteForm facilityCompleteForm = FacilityCompleteForm.getInstance();

		if(this.facilitySession.getFacilityForm().getName() == null) {
			facilityCompleteForm.setSysMsg(msg.getMessage("Session.lost",null, Locale.JAPAN));
			ra.addFlashAttribute("data", facilityCompleteForm);
			return "redirect:/facility/complete";
		}

		FacilityManagerDto facilityManagerDto = this.facilityCompleteHelper.convertFacilityManagerDto(this.facilitySession);
		facilityManagerDto.getFacilityDto().setUserDto(UserDto.getInstance());
		facilityManagerDto.getFacilityDto().getUserDto().setUserId(userDetailsImpl.getUserId());
		FacilityManagerDto dto = this.facilityManagerService.add(facilityManagerDto);
		facilityCompleteForm = this.facilityCompleteHelper.createFacilityCompleteForm(dto);
		facilityCompleteForm.setBtnAction(this.msg.getMessage("action.add", null, Locale.JAPAN ));
		facilityCompleteForm.setBtnName(this.msg.getMessage("label.add", null, Locale.JAPAN ));
		ra.addFlashAttribute("data", facilityCompleteForm);
		this.facilitySession.clear();
		System.out.println("complete-Facility-Cleaer" + this.facilitySession.getFacilityForm());
		return "redirect:/facility/complete";
	}

	@PostMapping(path="/facility/complete", params="update")
	public String createFacilityCompeteUpdate(ModelMap modelMap, RedirectAttributes ra, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		FacilityCompleteForm facilityCompleteForm = FacilityCompleteForm.getInstance();
		facilityCompleteForm.setBtnName(this.msg.getMessage("label.update", null, Locale.JAPAN ));
		FacilityManagerDto beforeDto = this.facilityCompleteHelper.convertFacilityManagerDto(this.facilitySession);
		beforeDto.getFacilityDto().getUserDto().setUserId(userDetailsImpl.getUserId());
		System.out.println("施設名称" + beforeDto.getFacilityDto().getName());
		FacilityManagerDto dto = this.facilityManagerService.update(beforeDto);

		switch(dto.getResult()) {
		case ServiceConst.OK:
			facilityCompleteForm.setSysMsg("更新が完了しました");
			break;
		case ServiceConst.OPTIMISTIC_ROCK_ERROR:
			facilityCompleteForm.setSysMsg(this.msg.getMessage("User.optimistic", null, Locale.JAPAN ));
			break;
		default:
			return "redirect:/error/error";
		}
		ra.addFlashAttribute("data", facilityCompleteForm);
		this.facilitySession.clear();

		return "redirect:/facility/complete";

	}

	@PostMapping(path="/facility/complete", params="delete")
	public String createFacilityCompeteDelete(ModelMap modelMap, RedirectAttributes ra) {
		FacilityCompleteForm facilityCompleteForm = FacilityCompleteForm.getInstance();
		FacilityManagerDto dto = this.facilityCompleteHelper.convertFacilityManagerDto(this.facilitySession);
		boolean state = this.facilityManagerService.delete(dto);

		if(!state) {
			return "redirect:/error/error";
		}

		facilityCompleteForm.setBtnName(this.msg.getMessage("label.delete", null, Locale.JAPAN ));

		ra.addFlashAttribute("data", facilityCompleteForm);
		this.facilitySession.clear();

		return "redirect:/facility/complete";

	}
}
