//package jp.co.ginga.web.application.controller.facilityreservation.add;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import jp.co.ginga.web.application.util.session.facilityreservation.FacilityReservationSession;
//import jp.co.ginga.web.domain.service.facilityreservation.FacilityReservationManagerDto;
//import jp.co.ginga.web.domain.service.facilityreservation.FacilityReservationManagerService;
//
//@Controller
//public class FacilityReservationAddController {
//	@Autowired
//	FacilityReservationAddHelper facilityReservationAddHelper;
//
//	@Autowired
//	FacilityReservationSession facilityReservationSession;
//
//	@Autowired
//	FacilityReservationManagerService facilityReservationManagerService;
//
//
//	@GetMapping("/facilityreservation/add/{facilityId}/")
//	public String createFacilityReservationAdd(@PathVariable int facilityId, @RequestParam("year") int year, @RequestParam("month") int month, @RequestParam("date") int date, ModelMap modelMap) {
//		FacilityReservationAddForm facilityReservationAddForm;
//
//		if(modelMap.containsKey("errors")) {
//			facilityReservationAddForm = this.facilityReservationAddHelper.createFacilityReservationAddForm(this.facilityReservationSession);
//			String key = BindingResult.MODEL_KEY_PREFIX + "facilityReservationAddForm";
//			modelMap.addAttribute(key, modelMap.get("errors"));
//		}else {
//			this.facilityReservationSession.clear();
//			FacilityReservationManagerDto facilityReservationManagerDto = this.facilityReservationManagerService.getFacilityReservationList(facilityId, year, month, date);
//			facilityReservationAddForm = this.facilityReservationAddHelper.createFacilityReservationAddForm(facilityReservationManagerDto);
//		}
//
//		modelMap.addAttribute("facilityReservationAddForm", facilityReservationAddForm);
//
//		return "facilityreservation/facilityreservation-add";
//	}
//}
