package edu.java.lab2;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Шаблоны отчётов. Если файл шаблона отсутствует или пуст,
 * он создаётся в папке проекта из текста, хранящегося в этом классе.
 *
 * @author Белякова С.Е.
 * @version 7.0
 */
public class ReportTemplates {

    /**
     * Проверяет наличие файла шаблона и при необходимости создаёт его.
     *
     * @param name имя файла шаблона (report1.jrxml или report2.jrxml)
     * @throws IOException ошибка записи файла
     */
    public static void ensure(String name) throws IOException {
        File f = new File(name);
        if (f.exists() && f.length() > 0) return;

        String text;
        if (name.equals("report1.jrxml")) text = REPORT1;
        else if (name.equals("report2.jrxml")) text = REPORT2;
        else return;

        Files.write(f.toPath(), text.getBytes(StandardCharsets.UTF_8));
    }

    /** Шаблон для PDF: строгое оформление. */
    private static final String REPORT1 = """
<?xml version="1.0" encoding="UTF-8"?>
<jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd"
  name="report1" pageWidth="842" pageHeight="595" orientation="Landscape"
  columnWidth="802" leftMargin="20" rightMargin="20" topMargin="20" bottomMargin="20"
  whenNoDataType="AllSectionsNoDetail">
  <style name="base" isDefault="true" fontName="DejaVu Sans" fontSize="11"/>
  <style name="head" fontName="DejaVu Sans" fontSize="11" isBold="true"/>
  <style name="titleStyle" fontName="DejaVu Sans" fontSize="20" isBold="true"/>
  <style name="small" fontName="DejaVu Sans" fontSize="9"/>
  <queryString language="xPath"><![CDATA[/skatelist/skate]]></queryString>
  <field name="brand" class="java.lang.String"><fieldDescription><![CDATA[@brand]]></fieldDescription></field>
  <field name="model" class="java.lang.String"><fieldDescription><![CDATA[@model]]></fieldDescription></field>
  <field name="size" class="java.lang.String"><fieldDescription><![CDATA[@size]]></fieldDescription></field>
  <field name="level" class="java.lang.String"><fieldDescription><![CDATA[@level]]></fieldDescription></field>
  <field name="price" class="java.lang.String"><fieldDescription><![CDATA[@price]]></fieldDescription></field>
  <title><band height="50">
    <staticText><reportElement style="titleStyle" x="0" y="5" width="802" height="35"/>
      <textElement textAlignment="Center" verticalAlignment="Middle"/>
      <text><![CDATA[Каталог коньков]]></text></staticText>
  </band></title>
  <columnHeader><band height="28">
    <staticText><reportElement style="head" x="0" y="0" width="160" height="26"/><box><topPen lineWidth="1"/><bottomPen lineWidth="1"/></box><textElement verticalAlignment="Middle"/><text><![CDATA[Бренд]]></text></staticText>
    <staticText><reportElement style="head" x="160" y="0" width="240" height="26"/><box><topPen lineWidth="1"/><bottomPen lineWidth="1"/></box><textElement verticalAlignment="Middle"/><text><![CDATA[Модель]]></text></staticText>
    <staticText><reportElement style="head" x="400" y="0" width="100" height="26"/><box><topPen lineWidth="1"/><bottomPen lineWidth="1"/></box><textElement verticalAlignment="Middle"/><text><![CDATA[Размер]]></text></staticText>
    <staticText><reportElement style="head" x="500" y="0" width="160" height="26"/><box><topPen lineWidth="1"/><bottomPen lineWidth="1"/></box><textElement verticalAlignment="Middle"/><text><![CDATA[Уровень]]></text></staticText>
    <staticText><reportElement style="head" x="660" y="0" width="142" height="26"/><box><topPen lineWidth="1"/><bottomPen lineWidth="1"/></box><textElement verticalAlignment="Middle"/><text><![CDATA[Цена, руб.]]></text></staticText>
  </band></columnHeader>
  <detail><band height="24">
    <textField isBlankWhenNull="true"><reportElement x="0" y="0" width="160" height="22"/><box><bottomPen lineWidth="0.5" lineColor="#999999"/></box><textElement verticalAlignment="Middle"/><textFieldExpression><![CDATA[$F{brand}]]></textFieldExpression></textField>
    <textField isBlankWhenNull="true"><reportElement x="160" y="0" width="240" height="22"/><box><bottomPen lineWidth="0.5" lineColor="#999999"/></box><textElement verticalAlignment="Middle"/><textFieldExpression><![CDATA[$F{model}]]></textFieldExpression></textField>
    <textField isBlankWhenNull="true"><reportElement x="400" y="0" width="100" height="22"/><box><bottomPen lineWidth="0.5" lineColor="#999999"/></box><textElement verticalAlignment="Middle"/><textFieldExpression><![CDATA[$F{size}]]></textFieldExpression></textField>
    <textField isBlankWhenNull="true"><reportElement x="500" y="0" width="160" height="22"/><box><bottomPen lineWidth="0.5" lineColor="#999999"/></box><textElement verticalAlignment="Middle"/><textFieldExpression><![CDATA[$F{level}]]></textFieldExpression></textField>
    <textField isBlankWhenNull="true"><reportElement x="660" y="0" width="142" height="22"/><box><bottomPen lineWidth="0.5" lineColor="#999999"/></box><textElement verticalAlignment="Middle"/><textFieldExpression><![CDATA[$F{price}]]></textFieldExpression></textField>
  </band></detail>
  <pageFooter><band height="25">
    <textField><reportElement style="small" x="0" y="4" width="802" height="18"/><textElement textAlignment="Right"/><textFieldExpression><![CDATA["Страница " + $V{PAGE_NUMBER}]]></textFieldExpression></textField>
  </band></pageFooter>
</jasperReport>
""";

    /** Шаблон для HTML: цветной заголовок, другой порядок колонок. */
    private static final String REPORT2 = """
<?xml version="1.0" encoding="UTF-8"?>
<jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd"
  name="report2" pageWidth="842" pageHeight="595" orientation="Landscape"
  columnWidth="802" leftMargin="20" rightMargin="20" topMargin="20" bottomMargin="20"
  whenNoDataType="AllSectionsNoDetail">
  <style name="base" isDefault="true" fontName="DejaVu Sans" fontSize="11"/>
  <queryString language="xPath"><![CDATA[/skatelist/skate]]></queryString>
  <field name="brand" class="java.lang.String"><fieldDescription><![CDATA[@brand]]></fieldDescription></field>
  <field name="model" class="java.lang.String"><fieldDescription><![CDATA[@model]]></fieldDescription></field>
  <field name="size" class="java.lang.String"><fieldDescription><![CDATA[@size]]></fieldDescription></field>
  <field name="level" class="java.lang.String"><fieldDescription><![CDATA[@level]]></fieldDescription></field>
  <field name="price" class="java.lang.String"><fieldDescription><![CDATA[@price]]></fieldDescription></field>
  <title><band height="50">
    <staticText><reportElement mode="Opaque" backcolor="#1F4E79" forecolor="#FFFFFF" x="0" y="0" width="802" height="40"/>
      <textElement textAlignment="Center" verticalAlignment="Middle"><font size="22" isBold="true"/></textElement>
      <text><![CDATA[Каталог коньков]]></text></staticText>
  </band></title>
  <columnHeader><band height="28">
    <staticText><reportElement mode="Opaque" backcolor="#D9E2F3" x="0" y="0" width="240" height="26"/><textElement verticalAlignment="Middle"><font isBold="true"/></textElement><text><![CDATA[Модель]]></text></staticText>
    <staticText><reportElement mode="Opaque" backcolor="#D9E2F3" x="240" y="0" width="160" height="26"/><textElement verticalAlignment="Middle"><font isBold="true"/></textElement><text><![CDATA[Бренд]]></text></staticText>
    <staticText><reportElement mode="Opaque" backcolor="#D9E2F3" x="400" y="0" width="160" height="26"/><textElement verticalAlignment="Middle"><font isBold="true"/></textElement><text><![CDATA[Уровень]]></text></staticText>
    <staticText><reportElement mode="Opaque" backcolor="#D9E2F3" x="560" y="0" width="100" height="26"/><textElement verticalAlignment="Middle"><font isBold="true"/></textElement><text><![CDATA[Размер]]></text></staticText>
    <staticText><reportElement mode="Opaque" backcolor="#D9E2F3" x="660" y="0" width="142" height="26"/><textElement verticalAlignment="Middle"><font isBold="true"/></textElement><text><![CDATA[Цена, руб.]]></text></staticText>
  </band></columnHeader>
  <detail><band height="24">
    <textField isBlankWhenNull="true"><reportElement x="0" y="0" width="240" height="22"/><box><bottomPen lineWidth="0.5" lineColor="#999999"/></box><textElement verticalAlignment="Middle"/><textFieldExpression><![CDATA[$F{model}]]></textFieldExpression></textField>
    <textField isBlankWhenNull="true"><reportElement x="240" y="0" width="160" height="22"/><box><bottomPen lineWidth="0.5" lineColor="#999999"/></box><textElement verticalAlignment="Middle"/><textFieldExpression><![CDATA[$F{brand}]]></textFieldExpression></textField>
    <textField isBlankWhenNull="true"><reportElement x="400" y="0" width="160" height="22"/><box><bottomPen lineWidth="0.5" lineColor="#999999"/></box><textElement verticalAlignment="Middle"/><textFieldExpression><![CDATA[$F{level}]]></textFieldExpression></textField>
    <textField isBlankWhenNull="true"><reportElement x="560" y="0" width="100" height="22"/><box><bottomPen lineWidth="0.5" lineColor="#999999"/></box><textElement verticalAlignment="Middle"/><textFieldExpression><![CDATA[$F{size}]]></textFieldExpression></textField>
    <textField isBlankWhenNull="true"><reportElement x="660" y="0" width="142" height="22"/><box><bottomPen lineWidth="0.5" lineColor="#999999"/></box><textElement verticalAlignment="Middle"/><textFieldExpression><![CDATA[$F{price}]]></textFieldExpression></textField>
  </band></detail>
</jasperReport>
""";
}