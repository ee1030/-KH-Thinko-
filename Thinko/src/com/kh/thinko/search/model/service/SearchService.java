package com.kh.thinko.search.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import static com.kh.thinko.common.JDBCTemplate.*;
import com.kh.thinko.board.model.vo.PageInfo2;
import com.kh.thinko.board.model.vo.VBoard;
import com.kh.thinko.search.model.dao.SearchDAO;

public class SearchService {
	private SearchDAO dao = new SearchDAO();

	public PageInfo2 getPageInfo(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();
		
		map.put("currentPage", 
				(map.get("currentPage") == null)? 1
						: Integer.parseInt((String)map.get("currentPage")));
		
		String condition = createCondition(map);
		
		int listCount = 0;
		if((int)map.get("type") == 0) {
			listCount = dao.getListCount(conn, condition);
		} else {
			listCount = dao.getListTypeCount(conn, condition, (int)map.get("type"));
		}

		
		close(conn);
	      	     
	    return new PageInfo2((int)map.get("currentPage"), listCount);
		
	}

	private String createCondition(Map<String, Object> map) {

		String condition = null;
		
		String searchKey = (String)map.get("searchKey");
		String searchValue = (String)map.get("searchValue");
		
		// searchKey가 없을 때 == 처음 검색 하는 경우
		searchKey = (searchKey == null || searchKey.equals("")) ? "first" : searchKey;
		System.out.println(searchKey);
		
		switch(searchKey) {
		
	
		 case "title" : 
            condition = " BOARD_TITLE LIKE '%' || '" + searchValue + "' || '%' ";
            break;			// "BOARD_TITLE LIKE '%'  || '49' || '%' "
            
         case "content" : 
            condition = " BOARD_CONTENT LIKE '%' || '" + searchValue + "' || '%' ";
            break;
            
         case "titcont" : 
            condition = " (BOARD_TITLE LIKE '%' || '" + searchValue + "' || '%' "
                    + "OR BOARD_CONTENT LIKE '%' || '" + searchValue + "' || '%') "  ;                    
            break;
            
         case "writer" : 
            condition = " MEMBER_ID LIKE '%' || '" + searchValue + "' || '%' ";
            break;
         
		case "first" : 
			condition = "( BOARD_TITLE LIKE '%' || '" + searchValue + "' || '%' " + 
					" OR BOARD_CONTENT LIKE '%' || '" + searchValue + "' || '%' "+ 
					" OR MEMBER_ID LIKE '%' || '" + searchValue + "' || '%') ";
         
      
		}
		return condition;
	}

	public List<VBoard> searchBoardList(Map<String, Object> map, PageInfo2 pInfo) throws Exception {
		Connection conn = getConnection();
	      
	    String condition = createCondition(map);
	    List<VBoard> bList = null;
	    if((int)map.get("type") == 0) {
	    	bList = dao.searchBoardList(conn, pInfo, condition, map);
	    } else {
	    	bList = dao.searchTypeBoardList(conn, pInfo, condition, map);
	    }
	    
	    close(conn);
	      
	    return bList;
	}

}
