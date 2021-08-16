package sample.spring.yse;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BookController {

	@Autowired
	BookService bookService;//책 입력 기능 서비스 호출을 위한 서비스 빈 추가!
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
		return new ModelAndView("book/create");
	}
	
	//책 입력 기능 컨트롤러
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(@RequestParam Map<String, Object> map) {
	    ModelAndView mav = new ModelAndView();

	    String bookId = this.bookService.create(map);
	    if (bookId == null) {
	        mav.setViewName("redirect:/create");
	    }else {
	        mav.setViewName("redirect:/detail?bookId=" + bookId); 
	    }  

	    return mav;
	}
	
	// 책 상세 화면 컨트롤러
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {//@RequestParam에 의해 쿼리 스트링 파라미터 읽을 수 있음.
	    Map<String, Object> detailMap = this.bookService.detail(map); // 쿼리 스트링 파라미터란, 주소창을 통해 파라미터가 서버로 전달되는 형태.

	    ModelAndView mav = new ModelAndView();
	    mav.addObject("data", detailMap);
	    String bookId = map.get("bookId").toString();
	    mav.addObject("bookId", bookId);
	    mav.setViewName("/book/detail");
	    return mav;
	}
	
	//책 수정 화면 컨트롤러
	@RequestMapping(value = "/update", method = RequestMethod.GET)  
	public ModelAndView update(@RequestParam Map<String, Object> map) {  
	Map<String, Object> detailMap = this.bookService.detail(map);  

	ModelAndView mav = new ModelAndView();  
	mav.addObject("data", detailMap);  
	mav.setViewName("/book/update");  
	return mav;  
	}  
	
	//첵 수정 기능 컨트롤러
	@RequestMapping(value = "update", method = RequestMethod.POST)  
	public ModelAndView updatePost(@RequestParam Map<String, Object> map) {  
	ModelAndView mav = new ModelAndView();  

	boolean isUpdateSuccess = this.bookService.edit(map);  
	if (isUpdateSuccess) {  
	String bookId = map.get("bookId").toString();  
	mav.setViewName("redirect:/detail?bookId=" + bookId);  
	}else {  
	mav = this.update(map);  
	}  

	return mav;  
	}  
	
	//책 삭제 기능 컨트롤러
	@RequestMapping(value = "/delete", method = RequestMethod.POST)  
	public ModelAndView deletePost(@RequestParam Map<String, Object> map) {  
	ModelAndView mav = new ModelAndView();  

	boolean isDeleteSuccess = this.bookService.remove(map);  
	if (isDeleteSuccess) {  
	mav.setViewName("redirect:/list");  
	}else {  
	String bookId = map.get("bookId").toString();  
	mav.setViewName("redirect:/detail?bookId=" + bookId);  
	}  

	return mav;  
	}  
	
	//책 목록 컨트롤러
	@RequestMapping(value = "list")  
	public ModelAndView list(@RequestParam Map<String, Object> map) {  

	List<Map<String, Object>> list = this.bookService.list(map);  

	ModelAndView mav = new ModelAndView();  
	mav.addObject("data", list);  
	
//	if (map.containsKey("keyword")) { // 검색 기능 추가  
//		mav.addObject("keyword", map.get("keyword"));  
//	} 
	
	mav.setViewName("/book/list");  
	return mav;  
	}  
	
}
