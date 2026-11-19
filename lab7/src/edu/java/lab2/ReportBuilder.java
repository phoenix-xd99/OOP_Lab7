package edu.java.lab2;

import java.io.File;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;

/**
 * Построение отчётов по XML-данным в форматах PDF и HTML (JasperReports).
 *
 * @author Белякова С.Е.
 * @version 7.0
 */
public class ReportBuilder {

    /**
     * Формирует отчёт по шаблону jrxml и данным XML-файла.
     * Формат отчёта определяется по расширению файла отчёта.
     *
     * @param datasource имя XML-файла с данными
     * @param xpath      XPath-запрос к узлам данных
     * @param template   имя jrxml-файла шаблона
     * @param resultpath имя файла отчёта (.pdf или .html)
     * @return полный путь к созданному файлу отчёта
     * @throws JRException ошибка построения отчёта
     */
    public static String print(String datasource, String xpath, String template, String resultpath)
            throws JRException {
    	
        // Создание файла шаблона, если его нет
        try {
            ReportTemplates.ensure(template);
        } catch (java.io.IOException e) {
            throw new JRException("Не удалось создать шаблон " + template + ": " + e.getMessage());
        }
        
        // Проверка наличия входных файлов
        File data = new File(datasource);
        File tpl = new File(template);
        if (!data.exists())
            throw new JRException("Не найден файл с данными: " + data.getAbsolutePath());
        if (!tpl.exists())
            throw new JRException("Не найден шаблон отчёта: " + tpl.getAbsolutePath());

        // Источник данных, компиляция шаблона, заполнение отчёта
        JRDataSource ds = new JRXmlDataSource(datasource, xpath);
        JasperReport report = JasperCompileManager.compileReport(template);
        JasperPrint print = JasperFillManager.fillReport(report, new HashMap<String, Object>(), ds);

        if (print.getPages().isEmpty())
            throw new JRException("В отчёте нет страниц: проверьте шаблон и данные");

        // Выгрузка в файл в нужном формате
        if (resultpath.toLowerCase().endsWith("pdf"))
            JasperExportManager.exportReportToPdfFile(print, resultpath);
        else
            JasperExportManager.exportReportToHtmlFile(print, resultpath);

        return new File(resultpath).getAbsolutePath();
    }
}