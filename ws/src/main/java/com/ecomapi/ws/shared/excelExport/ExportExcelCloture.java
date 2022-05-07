package com.ecomapi.ws.shared.excelExport;


import com.ecomapi.ws.response.CommandeResponse;
import com.ecomapi.ws.shared.Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Slf4j
public class ExportExcelCloture extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
         Utils utils = new Utils();
        // define excel file name to be exported
        response.addHeader("Content-Disposition", "attachment;fileName=Commande_Initial.xlsx");

        // Hna Kay9ra Data Mn Controllers
        @SuppressWarnings("unchecked")
        List<CommandeResponse> list = (List<CommandeResponse>) model.get("commandeInitial");

        // create one sheet
        Sheet sheet = workbook.createSheet("Commande_Initial");
        // STYLE CELL
        CellStyle cellStyle4 = workbook.createCellStyle();
        cellStyle4.setAlignment(HorizontalAlignment.CENTER);
        // create row0 as a header
        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("Numéro de Commande");
        row0.createCell(1).setCellValue("                   Article                 ");
        row0.createCell(2).setCellValue("                Nom De Client                  ");
        row0.createCell(3).setCellValue("   Téléphone   ");
        row0.createCell(4).setCellValue("       Ville         ");
        row0.createCell(5).setCellValue("    Prix Unitaire    ");
        row0.createCell(6).setCellValue("     Quantité   ");
        row0.createCell(7).setCellValue(" Date De Commande  ");
        row0.createCell(8).setCellValue("   Total  ");

        // FOR AUTO SIZE
        for (int j = 0; j < row0.getPhysicalNumberOfCells(); j++) {
            sheet.autoSizeColumn((short) j);
        }

        // create row1 onwards from List<T>
        int rowNum = 1;
        for(CommandeResponse cmdi : list) {
            Row row = sheet.createRow(rowNum++);
            sheet.autoSizeColumn(list.size());
            row.createCell(0).setCellValue(cmdi.getId());
         // GET ALL DATA TO CONTROLES
           // row.createCell(1).setCellValue(cmdi.getProduct().getTitleArticle());
            row.createCell(2).setCellValue(cmdi.getFirstName());
            row.createCell(3).setCellValue(cmdi.getNumber());
            row.createCell(4).setCellValue(cmdi.getCity());
          //  row.createCell(5).setCellValue(cmdi.getProduct().getPrice()+" MAD");
            row.createCell(7).setCellValue(utils.ConvertDateToStr(cmdi.getDateCommande()));
         //   row.createCell(8).setCellValue(cmdi.getProduct().getPrice()*cmdi.getQte() + "MAD");
        };
}
}