package jp.co.ginga.web.application.controller.facility.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.ginga.web.application.util.session.facility.FacilitySession;
import jp.co.ginga.web.domain.service.facility.FacilityManagerDto;
import jp.co.ginga.web.domain.service.facility.FacilityManagerService;

@Controller
public class FacilityListController {

	@Autowired
	FacilityListHelper facilityListHelper;

	/**
	 * 施設サービス
	 */
	@Autowired
	FacilityManagerService facilityManagerService;

	/**
	 * セッション
	 */
	@Autowired
	FacilitySession facilitySession;


	/**
	 *施設一覧画面のURLにアクセスした場合の動作
	 * @param model:Model
	 * @return String
	 */
	@GetMapping("/facility/list")
	public String getFacilityList(ModelMap model) {
		this.facilitySession.clear();
		FacilityManagerDto facilityManagerDto = this.facilityManagerService.getFacilityList();
		FacilityListForm facilityListForm = this.facilityListHelper.createFacilityListForm(facilityManagerDto);

		model.addAttribute("facilityListForm", facilityListForm);
		return "facility/facility-list";
	}
}
