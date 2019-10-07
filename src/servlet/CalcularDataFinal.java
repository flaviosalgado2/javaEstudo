package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoCalculaDataFinal;

@WebServlet("/pages/calcularDataFinal")
public class CalcularDataFinal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoCalculaDataFinal calculaDataFinal = new DaoCalculaDataFinal();

	public CalcularDataFinal() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			int horaDia = 8;
			// date pacote util
			Date dataCalculada = null;
			Double totalDeDias = 0.0;

			String data = request.getParameter("data");

			int tempo = Integer.parseInt(request.getParameter("tempo"));

			if (tempo <= horaDia) {// mesmo dia
				Date dateInfomada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
				Calendar calendar = Calendar.getInstance();					
				
				calendar.setTime(dateInfomada);
				//dois dias
				calendar.add(Calendar.DATE, 2);
				
				dataCalculada = calendar.getTime();
				totalDeDias = 1.0;
				
				dataCalculada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			} else {
				
				
				totalDeDias = (double) (tempo / horaDia);
				
				if (totalDeDias < 1) {
		
					
					dataCalculada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
				} else {
					
					Date dateInfomada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
					Calendar calendar = Calendar.getInstance();					
					
					calendar.setTime(dateInfomada);
					calendar.add(Calendar.DATE, totalDeDias.intValue());
					
					dataCalculada = calendar.getTime();
					
				}
				
				calculaDataFinal.gravaDataFinal(new SimpleDateFormat("dd/MM/yyyy").format(dataCalculada));
				
				RequestDispatcher dispacher = request.getRequestDispatcher("/pages/datas.jsp");
				
				request.setAttribute("dataFinal", new SimpleDateFormat("dd/MM/yyyy").format(dataCalculada));
				request.setAttribute("dias", totalDeDias);
				dispacher.forward(request, response);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
