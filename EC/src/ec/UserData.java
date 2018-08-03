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
import beans.UserDataBeans;
import dao.BuyDAO;
import dao.UserDAO;

/**
 * ユーザー情報画面
 *
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserData")
public class UserData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// セッション開始
		HttpSession session = request.getSession();
		try {
			// ログイン時に取得したユーザーIDをセッションから取得
			int userId = (int) session.getAttribute("userId");
//			int id = (int) session.getAttribute("id");
			// 更新確認画面から戻ってきた場合Sessionから取得。それ以外はuserIdでユーザーを取得
			UserDataBeans udb = session.getAttribute("returnUDB") == null ? UserDAO.getUserDataBeansByUserId(userId) : (UserDataBeans) EcHelper.cutSessionAttribute(session, "returnUDB");



			// 入力された内容に誤りがあったとき等に表示するエラーメッセージを格納する
			String validationMessage = (String) EcHelper.cutSessionAttribute(session, "validationMessage");


			request.setAttribute("validationMessage", validationMessage);
			request.setAttribute("udb", udb);

			// get id from t_buy table
//			BuyDataBeans bdb = new BuyDataBeans();
//			int id = bdb.getId();

			// こっちをリスト化
			ArrayList<BuyDataBeans> BDBList =BuyDAO.getBuyDataBeansByUserId(userId);
			//リクエストパラメーターにセット
			request.setAttribute("BDBList", BDBList);
//			request.setAttribute("createDate", ((BuyDataBeans) BDBList).getBuyDate());
//			request.setAttribute("deliveryMethodName", ((BuyDataBeans) BDBList).getDeliveryMethodName());
//			request.setAttribute("totalPrice", ((BuyDataBeans) BDBList).getTotalPrice());

//			BuyDetailDAO buyDetailDAO = new BuyDetailDAO();
//			// name, price
//			List<ItemDataBeans>  IDBList = buyDetailDAO.getItemDataBeansListByBuyId(userId);
//
//			request.setAttribute("IDBList", IDBList);

			request.getRequestDispatcher(EcHelper.USER_DATA_PAGE).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
		// 購入履歴リスト（購入日時、配送方法、購入金額）を取得、結果を表示するコードを書く
		// BuyDaoの getBuyDataBeansByBuyId をつかう

		//購入履歴リスト（購入日時、配送方法、購入金額）情報を取得①

		//リクエストパラメーターにセット②


}
