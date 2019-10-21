package com.zuri.circle.manager.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zuri.circle.manager.Request.DonationReport;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
@Service
public class GeneratePdfReportService {
	public static Logger logger = LogManager.getLogger(DonorService.class);
	
	public ByteArrayInputStream generatedonationReport(DonationReport donationReport) {
		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(60);
            table.setWidths(new int[]{1, 3, 3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("DonationId", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Name", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Amount", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);


                PdfPCell cell;

                cell = new PdfPCell(new Phrase(donationReport.getDonationId().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(donationReport.getDonorName()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(donationReport.getAmount())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
                
            PdfWriter.getInstance(document, out);
            Phrase title = new Phrase("Donation Receipt", headFont);
            title.setFont(headFont);
            Phrase bestRegards = new Phrase("Best Regards", headFont);
            title.setFont(headFont);
            Phrase zuriCircle = new Phrase("Zuri Circle", headFont);
            title.setFont(headFont);
            Paragraph preface = new Paragraph();
            preface.add(new Paragraph(" We have received your donation on " + new Date())+". Thank you for you kind donation.");
            document.open();
            document.add(title);
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );
            document.add(table);
            document.add( Chunk.NEWLINE );
            document.add(preface);
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );
            document.add(bestRegards);
            document.add( Chunk.NEWLINE );
            document.add(zuriCircle);
            document.close();

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
	}
	
	

}
