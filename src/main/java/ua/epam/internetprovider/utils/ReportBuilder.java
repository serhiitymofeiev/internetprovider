package ua.epam.internetprovider.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.servlet.http.HttpServletResponse;
import ua.epam.internetprovider.db.entity.Tariff;
import ua.epam.internetprovider.db.entity.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.Set;

/**
 * Class for building PDF reports
 */
public class ReportBuilder {
    /**
     * The procedure for obtaining a printed PDF form of the user's card
     * @param response servlet response used to display the report and retrieve user data
     * @param user user object
     */
    public static void contractPDF(HttpServletResponse response, User user) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            // if you want to save on the server
            //PdfWriter.getInstance(document, new FileOutputStream("D:\\Projects\\IdeaProjects\\InternetProvider\\contract" + user.getAccount().getNumber() + ".pdf"));
            PdfWriter.getInstance(document, baos);
            document.open();
            addMetaData(document);

            BaseFont myfont = BaseFont.createFont("C:\\Windows\\Fonts\\times.ttf", "cp1251", BaseFont.EMBEDDED);

            Paragraph title = new Paragraph("Договір надання послуг №" + user.getAccount().getNumber(), new Font(myfont, 16,Font.BOLDITALIC+Font.UNDERLINE));
            title.setAlignment(Element.ALIGN_CENTER);

            // We add one empty line
            addEmptyLine(title, 1);

            Chapter chapter = new Chapter(title,1);

            chapter.setNumberDepth(0);

            Paragraph title1 = new Paragraph("Постачальник послуг: ФОП «Провайдер»", new Font(myfont, 10));
            addEmptyLine(title1, 1);

            Section section1 = chapter.addSection(title1);
            section1.setNumberDepth(0);

            Paragraph personalInformation = new Paragraph("П.І.Б.: " + user.getSurname() + " " + user.getFirstName() + " " + user.getLastName(), new Font(myfont, 12));
            section1.add(personalInformation);

            String address = "Адреса: " + user.getDetails().getCity() + ", " +
                    user.getDetails().getStreet() +
                    " " + user.getDetails().getHome() +
                    ", кв. " + user.getDetails().getApartment();
            personalInformation = new Paragraph(address, new Font(myfont, 12));
            section1.add(personalInformation);
            addEmptyLine(personalInformation, 1);
            personalInformation = new Paragraph("Логін та пароль для входу в особистий кабінет:", new Font(myfont, 14,Font.BOLDITALIC+Font.UNDERLINE));
            personalInformation.setAlignment(Element.ALIGN_CENTER);

            section1.add(personalInformation);

            personalInformation = new Paragraph("Логін: " + user.getDetails().getPhone(), new Font(myfont, 12));
            section1.add(personalInformation);

            personalInformation = new Paragraph("Пароль: " + user.getPassword(), new Font(myfont, 12));
            section1.add(personalInformation);

            addEmptyLine(personalInformation, 1);

            PdfPTable t = new PdfPTable(3);
            //Set Column widths
            float[] columnWidths = {1f, 1f, 2.5f};
            t.setWidths(columnWidths);

            t.setSpacingBefore(10);
            t.setSpacingAfter(10);

            PdfPCell c1 = new PdfPCell(new Phrase("Тариф", new Font(myfont, 12, Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_CENTER);
            t.addCell(c1);

            PdfPCell c2 = new PdfPCell(new Phrase("Вартість", new Font(myfont, 12, Font.BOLD)));
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            c2.setVerticalAlignment(Element.ALIGN_CENTER);
            t.addCell(c2);

            PdfPCell c3 = new PdfPCell(new Phrase("Опис", new Font(myfont, 12, Font.BOLD)));
            c3.setHorizontalAlignment(Element.ALIGN_CENTER);
            c3.setVerticalAlignment(Element.ALIGN_CENTER);
            t.addCell(c3);

            Set<Tariff> tariffs = user.getTariffs();

            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(response.getLocale());

            for (Tariff tariff : tariffs) {
                t.addCell(new Phrase(tariff.getName(), new Font(myfont, 12)));
                t.addCell(new Phrase(currencyFormatter.format(tariff.getPrice()), new Font(myfont, 12)));
                t.addCell(new Phrase(tariff.getDescription(), new Font(myfont, 12)));
            }
            section1.add(t);

            Paragraph footer = new Paragraph("Фінальний проект EPAM", new Font(myfont, 10));
            section1.add(footer);

            document.add(chapter);
            document.close();

            openInBrowser(response, baos);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The procedure for obtaining a printed PDF form of the tariff card
     * @param response servlet response used to display the report and retrieve user data
     * @param tariff tariff object
     */
    public static void tariffPDF(HttpServletResponse response, Tariff tariff) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            String tariffName = tariff.getName();
            if (tariffName.contains("*") || tariffName.contains("\\") || tariffName.contains("/") || tariffName.contains(":") ||
                    tariffName.contains("?") || tariffName.contains("<") || tariffName.contains(">") || tariffName.contains("\"")) {
                tariffName = tariff.getName().replace('*', '_');
            }

            // if you want to save on the server
            //PdfWriter.getInstance(document, new FileOutputStream("D:\\Projects\\IdeaProjects\\InternetProvider\\tariff_" + tariffName + ".pdf"));
            PdfWriter.getInstance(document, baos);
            document.open();

            BaseFont myfont = BaseFont.createFont("C:\\Windows\\Fonts\\times.ttf", "cp1251", BaseFont.EMBEDDED);

            String name = "Тариф <" + tariff.getName() + ">";
            Paragraph title = new Paragraph(name, new Font(myfont, 16));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            addEmptyLine(title, 1);
            PdfPTable t = new PdfPTable(3);

            t.setSpacingBefore(5);
            t.setSpacingAfter(5);

            PdfPCell c1 = new PdfPCell(new Phrase("Тариф", new Font(myfont, 12, Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            t.addCell(c1);

            PdfPCell c2 = new PdfPCell(new Phrase("Вартість", new Font(myfont, 12, Font.BOLD)));
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            t.addCell(c2);

            PdfPCell c3 = new PdfPCell(new Phrase("Опис", new Font(myfont, 12, Font.BOLD)));
            c3.setHorizontalAlignment(Element.ALIGN_CENTER);
            t.addCell(c3);

            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(response.getLocale());

            t.addCell(new Phrase(tariff.getName(), new Font(myfont, 12)));
            t.addCell(new Phrase(currencyFormatter.format(tariff.getPrice()), new Font(myfont, 12)));
            t.addCell(new Phrase(tariff.getDescription(), new Font(myfont, 12)));

            document.add(t);

            Paragraph footer = new Paragraph("Фінальний проект EPAM", new Font(myfont, 10));
            document.add(footer);
            document.close();

            openInBrowser(response, baos);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }


    private static void openInBrowser(HttpServletResponse response, ByteArrayOutputStream baos) {
        // setting some response headers
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        // setting the content type
        response.setContentType("application/pdf");
        // the contentlength
        response.setContentLength(baos.size());
        // write ByteArrayOutputStream to the ServletOutputStream
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("My PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("EPAM");
        document.addCreator("EPAM");
    }

}
