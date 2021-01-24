package com.kh.thinko.board.model.service;

import static com.kh.thinko.common.JDBCTemplate.close;
import static com.kh.thinko.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.kh.thinko.board.model.dao.MainDAO;
import com.kh.thinko.board.model.vo.VBoard;

public class MainService {

	private MainDAO dao = new MainDAO();
	
	/**
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<VBoard> BoardList(int type) throws Exception{
		
		Connection conn = getConnection();
		
		List<VBoard> bList = null;
		
		if(type == 20) {
			bList = dao.publicBoardList(conn, type);
		}else {
			bList = dao.otherBoardList(conn, type);
		}
		
		close(conn);
		
		return bList;
	}

}
