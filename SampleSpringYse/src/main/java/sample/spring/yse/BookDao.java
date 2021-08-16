package sample.spring.yse;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int insert(Map<String, Object> map) {
		  return this.sqlSessionTemplate.insert("book.insert", map);
		}
	
	public Map<String, Object> selectDetail(Map<String, Object> map) {
	    return this.sqlSessionTemplate.selectOne("book.select_detail", map);
	}
	
	public int update(Map<String, Object> map) {  
		return this.sqlSessionTemplate.update("book.update", map);  
	}  //반환값은 영향받은 행의 수, 즉 수정된 db행의 갯수
	
	public int delete(Map<String, Object> map) {  
		return this.sqlSessionTemplate.delete("book.delete", map);  
	}  

	public List<Map<String, Object>> selectList(Map<String, Object> map) {  
		return this.sqlSessionTemplate.selectList("book.select_list", map);  
	}  
}