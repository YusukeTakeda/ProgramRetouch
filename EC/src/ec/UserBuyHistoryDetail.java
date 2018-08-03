package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import beans.ItemDataBeans;
import dao.BuyDAO;
import dao.BuyDetailDAO;

/**
 * 購入履歴画面
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserBuyHistoryDetail")
public class UserBuyHistoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッション開始
		HttpSession session = request.getSession();

		try {
			// ログイン時に取得したユーザーIDをセッションから取得
			int userId = (int) session.getAttribute("userId");

			int buyId = Integer.parseInt(request.getParameter("id"));

			/* ====詳細ページ表示用==== */
			// 購入日時、配送方法、合計金額

			BuyDataBeans bdb = new BuyDataBeans();
			bdb = BuyDAO.getBuyDataBeansByBuyId(buyId);
			request.setAttribute("bdb", bdb);
//
//			ArrayList<BuyDataBeans> BDBList =BuyDAO.getBuyDataBeansByUserId(userId);
//			//リクエストパラメーターにセット
//			request.setAttribute("BDBList", BDBList);

//
//			BuyDataBeans resultBDB = BuyDAO.getBuyDataBeansByBuyId(buyId);
//			request.setAttribute("resultBDB", resultBDB);

			// 購入アイテム情報(商品名、単価)
			ArrayList<ItemDataBeans> buyIDBList = BuyDetailDAO.getItemDataBeansListByBuyId(buyId);
			request.setAttribute("buyIDBList", buyIDBList);


//
//			BuyDetailDAO buyDetailDAO = new BuyDetailDAO();
//			ItemDataBeans itemDataBeans = new ItemDataBeans();
//
//			ArrayList<ItemDataBeans> buyDetailItemList = buyDetailDAO.getItemDataBeansListByBuyId(buyId);
//			request.setAttribute("buyDetailItemList", buyDetailItemList);
//			request.setAttribute("name", itemDataBeans.getName());
//			request.setAttribute("price", itemDataBeans.getPrice());

			request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}

		// 購入履歴リスト（購入日時、配送方法、購入金額）を取得、結果を表示するコードを書く
		// BuyDaoの getBuyDataBeansByBuyId をつかう

		//購入履歴リスト（購入日時、配送方法、購入金額）情報を取得①

		//リクエストパラメーターにセット②
	}
}
