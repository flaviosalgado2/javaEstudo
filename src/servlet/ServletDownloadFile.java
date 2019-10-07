package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuario;
import entidades.Usuario;
import service.RelatorioService;

@WebServlet("/pages/ServletDownloadFile")
public class ServletDownloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RelatorioService relatorioService = new RelatorioService();

	private DaoUsuario daoUsuario = new DaoUsuario();

	public ServletDownloadFile() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			ServletContext context = request.getServletContext();

			String tipoExportar = request.getParameter("tipoExportar");

			List<Usuario> usuarios = daoUsuario.getUsuarios();

			String fileUrl = relatorioService.gerarRelatorio(usuarios, new HashMap(), "rel_usuario", "rel_usuario",
					context, tipoExportar);

			// construir o caminho completo e absoluto do arquivo
			File downloadFIle = new File(fileUrl);
			FileInputStream inputStream = new FileInputStream(downloadFIle);

			// obter o tipo MIME do arquivo
			String mimeType = context.getMimeType(fileUrl);

			if (mimeType == null) {
				// define como tipo binario se mapeamento mime não for encontrado
				mimeType = "application/octet-stream";
			}

			// define atributos para resposta
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFIle.length());

			// definir cabeçalhos para a resposta
			String headerKey = "Content-Disposition";
			String headerValeu = String.format("attachment; filename=\"%s\"", downloadFIle.getName());

			response.setHeader(headerKey, headerValeu);

			// obter fluxo de saida da resposta
			OutputStream outputStream = response.getOutputStream();

			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			// escrever bytes lidos a partir do fluxo de entrada para o fluxo de saida
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			
			inputStream.close();
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
