package service;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioService implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String FOLDER_RELATORIOS = "/relatorios";

	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private String SEARATOR = File.pathSeparator;
	private String caminhoArquivoRelatorio = null;
	private JRExporter exporter = null;
	private String caminhoSubReport_Dir = "";
	private File arquivoGerado = null;

	public String gerarRelatorio(List<?> listaDataBeanColletion, HashMap parametrosRelatorio,
			String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext, String tipoExportar)
			throws Exception {

		// cria lista de colletionDataSource de beans que carregam os dados para o
		// relatorio
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDataBeanColletion);

		// Fornece caminho fisico ate a pasta que contem os relatorios .jasper
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);

		File file = new File(caminhoRelatorio + SEARATOR + nomeRelatorioJasper + ".jasper");

		// pegar um caminho certo
		if (caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) || !file.exists()) {
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SEARATOR = "";
		}

		// caminho para imagens
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);

		// caminho correto ate o relatorio compilado indicado
		String caminhoArquivosJasper = caminhoRelatorio + SEARATOR + nomeRelatorioJasper + ".jasper";

		// faz o carregamento do relatorio
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivosJasper);

		// sera parametros SUBREPORT_DIR com o caminho fisico para subreport
		caminhoSubReport_Dir = caminhoRelatorio + SEARATOR;
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubReport_Dir);

		// carrega o arquivo
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrbcds);
		
		//exportes para tipo pdf e xls
		if (tipoExportar.equalsIgnoreCase("pdf")) {
			exporter = new JRPdfExporter();
		} else if (tipoExportar.equalsIgnoreCase("xls")) {
			exporter = new JRXlsExporter();
		}

		// caminho do relatorio
		caminhoArquivoRelatorio = caminhoRelatorio + SEARATOR + nomeRelatorioSaida + "." + tipoExportar;

		// criar novo arquivo exportar
		arquivoGerado = new File(caminhoArquivoRelatorio);

		// prepara a impressao
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);

		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);

		// executa a exportacao
		exporter.exportReport();

		// remove arquivo do servidor
		arquivoGerado.deleteOnExit();

		return caminhoArquivoRelatorio;

	}
}
