package de.neasy.task.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import de.neasy.task.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(PDFGenerator.class);

    public static ByteArrayInputStream userPDFReport(List<User> userList) {
        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            //Add Text to PDF file
            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);

            PdfPTable table = new PdfPTable(7);
            // Add PDF Table Header ->
            Stream.of("Id", "FirstName", "LastName", "Address", "Email", "Date", "Picture")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setBorderWidth(1);
                        header.setPhrase(new Phrase(headerTitle, headFont));
                        table.addCell(header);
                    });

            for (User user : userList) {
                PdfPCell idCell = new PdfPCell(new Phrase(user.getId() + ""));
                idCell.setPaddingLeft(4);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);

                PdfPCell firstNameCell = new PdfPCell(new Phrase(user.getFirstName()));
                firstNameCell.setPaddingLeft(4);
                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(firstNameCell);

                PdfPCell lastNameCell = new PdfPCell(new Phrase(String.valueOf(user.getLastName())));
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                lastNameCell.setPaddingRight(4);
                table.addCell(lastNameCell);

                PdfPCell addressCell = new PdfPCell(new Phrase(String.valueOf(user.getAddress())));
                addressCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                addressCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                addressCell.setPaddingRight(4);
                table.addCell(addressCell);

                PdfPCell emailCell = new PdfPCell(new Phrase(String.valueOf(user.getEmail())));
                emailCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                emailCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                emailCell.setPaddingRight(4);
                table.addCell(emailCell);

                PdfPCell dateCell = new PdfPCell(new Phrase(String.valueOf(user.getDate())));
                dateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                dateCell.setPaddingRight(4);
                table.addCell(dateCell);

                PdfPCell pictureCell = new PdfPCell(new Phrase(String.valueOf(user.getFilePath())));
                pictureCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                pictureCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pictureCell.setPaddingRight(4);
                table.addCell(pictureCell);
            }
            document.add(table);

            document.close();

        } catch (DocumentException e) {
            logger.error(e.toString());
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
