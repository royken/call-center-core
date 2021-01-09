package com.bracongo.callcenter.service.impl;

import com.bracongo.callcenter.entities.Commande;
import com.bracongo.callcenter.entities.CommandeItem;
import com.bracongo.callcenter.entities.Utilisateur;
import com.bracongo.callcenter.entities.dto.CommandeDto;
import com.bracongo.callcenter.entities.dto.CommandeStatutUpdateDto;
import com.bracongo.callcenter.repository.ICommandeItemRepository;
import com.bracongo.callcenter.repository.ICommandeRepository;
import com.bracongo.callcenter.repository.IUtilisateurRepository;
import com.bracongo.callcenter.service.IcommandeService;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vr.kenfack
 */
@Service
@Transactional
public class CommandeServiceImpl implements IcommandeService {

    @Autowired
    private ICommandeRepository commandeRepository;

    @Autowired
    private ICommandeItemRepository commandeItemRepository;

    @Autowired
    private IUtilisateurRepository utilisateurRepository;

    @Autowired
    private JavaMailSender sender;

    float llx;
    float lly;
    float urx;
    float ury;

    XSSFColor b = new XSSFColor(new java.awt.Color(255, 255, 255));

    XSSFCellStyle grey;

    XSSFCellStyle black;

    XSSFCellStyle gold;

    XSSFCellStyle blue;

    XSSFCellStyle yellow;

    XSSFCellStyle myStyle;

    XSSFCellStyle myStyle2;

    XSSFCellStyle myStyle3;

    XSSFCellStyle cdHeader;

    XSSFCellStyle footer;

    @Override
    public Commande saveCommande(CommandeDto commande) {
        Utilisateur u = utilisateurRepository.findByUsername(commande.getUsername().trim());
        if (u != null) {
            System.out.println(commande);
            Commande commande_ = new Commande();
            commande_.setClient(commande.getClient());
            commande_.setQuantite(commande.getQuantite());
            commande_.setPrixTotal(commande.getPrixTotal());
            commande_.setDateCommande(new Date());
            commande_.setUtilisateur(u);
            commande_.setStatut("INITIEE");
            commande_.setSup(commande.getSup());
            commande_.setTelSup(commande.getTelSup());
            commande_.setMailSup(commande.getMailSup());
            commande_.setMerch(commande.getMerch());
            commande_.setTelMerch(commande.getTelMerch());
            commande_.setMailMerch(commande.getMailMerch());
            commande_.setRaisonSociale(commande.getRaisonSociale());
            commande_.setNomProprio(commande.getNomProprio());
            commande_.setNumTelClient(commande.getNumTelClient());
            commande_.setCircuit(commande.getCircuit());
            commande_.setCd(commande.getCd());
            commande_ = commandeRepository.save(commande_);
            List<CommandeItem> items = commande.getItems();
            for (CommandeItem item : items) {
                item.setCommande(commande_);
                commandeItemRepository.save(item);
            }
            // commande_.setClient(null);
            // commande_.setCommandeItems(null);
            if (commande_ != null) {
                try {
                    sendEmail(commande);
                    sendSMS(commande_.getTelSup(), "Nouvelle commande du client " + commande.getClient() + " de " + commande.getQuantite() + " produits, pour " + commande.getPrixTotal() + " FC");
                    String telClient = commande.getNumTelClient().trim();
                    if (telClient != null && telClient.length() >= 9) {
                        sendSMSToClient(commande_.getNumTelClient().trim(), "Bonjour, votre commande de " + commande.getQuantite() + " produit(s) d'un montant total de " + commande.getPrixTotal() + " FC a bien été enregistrée. Bracongo vous remercie.");
                    }

                    submitMessage();
                } catch (Exception ex) {
                    Logger.getLogger(CommandeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return commande_;
        }
        return null;
    }

    @Override
    public Commande getCommandeById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCommande(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commande> getAllCommandes() {
        Calendar cal = Calendar.getInstance();
        Date fin = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, -7);
        Date debut = cal.getTime();
        List<Commande> commandes = commandeRepository.findAllByDateCommandeBetween(debut, fin);
        List<Commande> result = new ArrayList<>();
        commandes.stream().map((commande) -> {
            List<CommandeItem> items = commandeItemRepository.findByCommande(commande);
            commande.setCommandeItems(items);
            return commande;
        }).forEachOrdered((commande) -> {
            result.add(commande);
        });
        return result;
    }

    @Override
    public Commande updateStatut(CommandeStatutUpdateDto statut) {
        Commande commande = commandeRepository.getOne(statut.getId());
        commande.setStatut(statut.getStatut().trim());
        if (statut.getStatut().trim().equalsIgnoreCase("ANNULEE")) {
            commande.setDateAnnulation(new Date());
            commande.setUserAnnulateur(statut.getUsername().trim());
            commande.setActive(true);
        }
        if (statut.getStatut().trim().equalsIgnoreCase("LIVREE")) {
            commande.setDateLivraison(new Date());
            commande.setUserLivraison(statut.getUsername().trim());
        }
        return commandeRepository.save(commande);
    }

    private void sendEmail(CommandeDto commande) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setCc(new String[]{commande.getMailSup(), commande.getMailMerch(), "eric.nkedi@castel-afrique.com"});
        helper.setTo("conso@bracongo.cd");
        helper.setText(buildCommandeMailBody(commande));
        helper.setSubject("Call Center Notification : Nouvelle Commande");
        helper.setFrom("rdsid@bracongo.cd");

        sender.send(message);
    }

    private String buildCommandeMailBody(CommandeDto commande) {
        System.out.println("sending mail");
        String string = "Bonjour, \n Nouvelle commande de l'agent : " + commande.getUsername() + "\n\n"
                + "---------------------------------------------- Commande--------------------------------\n"
                + ("Client : " + commande.getClient() + "  -   " + commande.getRaisonSociale() + "      Qte : " + commande.getQuantite() + "      " + "Prix Total " + commande.getPrixTotal() + "FC" + "\n")
                + ("----------------------------------------------------------------------------------------------\n\n")
                + ("Produit                 ||   Qte   ||   PU      ||   PT\n")
                + ("----------------------------------------------------------------------------------------------\n\n");
        List<CommandeItem> items = commande.getItems();
        for (CommandeItem item : items) {
            string += item.getNomProduit() + " ||     " + item.getQuantite() + "     ||     " + item.getPrixUnitaire() + "FC" + "     ||    " + (item.getQuantite() * item.getPrixUnitaire()) + "FC" + "\n";
            string += "\n";
        }
        string += ("----------------------------------------------------------------------------------------------\n\n");
        return string;
    }

    private void sendSMS(String numDest, String corps) throws UnsupportedEncodingException, IOException {
        System.out.println("sending sms");
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://api.messaging-service.com/sms/1/text/single");

            String json = "{from : BracongoNotif, to : " + numDest + ", text : " + corps + "}";
            String jsonString = new JSONObject()
                    .put("from", "BracongoNotif")
                    .put("to", numDest)
                    .put("text", corps)
                    .toString();
            StringEntity entity = new StringEntity(jsonString);
            httpPost.setEntity(entity);
            //httpPost.setHeader("Authorization", "Basic QS5zZXZlcmluOkJyYWNvbmdvMjAxNw==");
            httpPost.setHeader(HttpHeaders.ACCEPT, "application/json");
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + "QS5zZXZlcmluOkJyYWNvbmdvMjAxNw==");
            System.out.println("POST : " + httpPost);
            CloseableHttpResponse response = client.execute(httpPost);
            // assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
            System.out.println("LA REPONSE : " + response);
            if (response.getStatusLine().getStatusCode() == 200) {
                // sms envoyé
            }
        }
    }

    private void sendSMSToClient(String numDest, String corps) throws UnsupportedEncodingException, IOException {
        System.out.println("sending sms");
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://api.messaging-service.com/sms/1/text/single");

            String json = "{from : Bracongo, to : " + numDest + ", text : " + corps + "}";
            String jsonString = new JSONObject()
                    .put("from", "Bracongo")
                    .put("to", numDest)
                    .put("text", corps)
                    .toString();
            StringEntity entity = new StringEntity(jsonString);
            httpPost.setEntity(entity);
            //httpPost.setHeader("Authorization", "Basic QS5zZXZlcmluOkJyYWNvbmdvMjAxNw==");
            httpPost.setHeader(HttpHeaders.ACCEPT, "application/json");
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + "QS5zZXZlcmluOkJyYWNvbmdvMjAxNw==");
            System.out.println("POST : " + httpPost);
            CloseableHttpResponse response = client.execute(httpPost);
            // assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
            System.out.println("LA REPONSE : " + response);
            if (response.getStatusLine().getStatusCode() == 200) {
                // sms envoyé
            }
        }
    }

    private void submitMessage() {
        try {
// Url that will be called to submit the message
            URL sendUrl = new URL("http://clientsms.com-tic.africa/rsms/SMSnoelloic/");
            HttpURLConnection httpConnection = (HttpURLConnection) sendUrl
                    .openConnection();
// This method sets the method type to POST so that
// will be send as a POST request httpConnection.setRequestMethod("POST");
// This method is set as true wince we intend to send
// input to the server
            httpConnection.setDoInput(true);
// This method implies that we intend to receive data from server.
            httpConnection.setDoOutput(true); // Implies do not use cached data 
            httpConnection.setUseCaches(false);
            try (// Data that will be sent over the stream to the server.
                    DataOutputStream dataStreamToServer = new DataOutputStream(
                            httpConnection.getOutputStream())) {
                dataStreamToServer.writeBytes("username="
                        + URLEncoder.encode("CT01-bracongo", "UTF-8") + "&password="
                        + URLEncoder.encode("Sms@9873", "UTF-8") + "&type="
                        + URLEncoder.encode("0", "UTF-8") + "&dlr="
                        + URLEncoder.encode("1", "UTF-8") + "&destination="
                        + URLEncoder.encode("+243828500297", "UTF-8") + "&source="
                        + URLEncoder.encode("Bracongo", "UTF-8")
                        + "&message=" + URLEncoder.encode("message", "UTF8")
                );
                dataStreamToServer.flush();
            }
// Here take the output value of the server.
            BufferedReader dataStreamFromUrl = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            String dataFromUrl = "", dataBuffer = ""; // Writinginformation from the stream to the buffer 
            while ((dataBuffer
                    = dataStreamFromUrl.readLine()) != null) {
                dataFromUrl
                        += dataBuffer;
            }
            /**
             * Now dataFromUrl variable contains the Response received from the
             * * server so we can parse the response and process it accordingly.
             */
            dataStreamFromUrl.close();
            System.out.println("Response: " + dataFromUrl);
        } catch (IOException ex) {
        }
    }

    @Override
    public byte[] exportCommandeReport(OutputStream out, Date debut, Date fin) {
        try {
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();

            cal.setTime(debut);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal2.setTime(fin);
            cal2.set(Calendar.HOUR_OF_DAY, 23);
            List<Commande> commandes = commandeRepository.findAllByDateCommandeBetween(cal.getTime(), cal2.getTime());
            List<CommandeItem> commandeItems = commandeItemRepository.findAllItemBetweenDate(cal.getTime(), cal2.getTime());
            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFFont boldFont = workbook.createFont();
            boldFont.setBold(true);

            myStyle = workbook.createCellStyle();
            myStyle.setRotation((short) 90);
            myStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GOLD.getIndex());
            myStyle.setBorderColor(XSSFCellBorder.BorderSide.LEFT, b);
            myStyle.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, b);
            myStyle.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, b);
            myStyle.setBorderColor(XSSFCellBorder.BorderSide.TOP, b);
            myStyle3 = workbook.createCellStyle();
            myStyle3.setRotation((short) 90);
            myStyle3.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_YELLOW.getIndex());
            myStyle3.setBorderColor(XSSFCellBorder.BorderSide.LEFT, b);
            myStyle3.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, b);
            myStyle3.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, b);
            myStyle3.setBorderColor(XSSFCellBorder.BorderSide.TOP, b);
            grey = workbook.createCellStyle();
            grey.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex());
            grey.setBorderColor(XSSFCellBorder.BorderSide.LEFT, b);
            grey.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, b);
            grey.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, b);
            grey.setBorderColor(XSSFCellBorder.BorderSide.TOP, b);
            grey.setAlignment(HorizontalAlignment.CENTER);
            grey.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            footer = workbook.createCellStyle();
            footer.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_ORANGE.getIndex());
            footer.setBorderColor(XSSFCellBorder.BorderSide.LEFT, b);
            footer.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, b);
            footer.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, b);
            footer.setBorderColor(XSSFCellBorder.BorderSide.TOP, b);
            footer.setAlignment(HorizontalAlignment.CENTER);
            footer.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            footer.setFont(boldFont);

            XSSFSheet rapportSheet = workbook.createSheet("Rapport Commandes");
            produceRapportSheet(rapportSheet, commandes, commandeItems);
            File file = File.createTempFile("Rapport", "xlsx");
            file.deleteOnExit();
            Path path = file.toPath();
            FileOutputStream fileOut = new FileOutputStream(file);
            /* workbook.write(fileOut);
            fileOut.close();
            DataSource fds = new FileDataSource("temp.xls");
             */
            workbook.write(out);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos); // write excel data to a byte array
            fileOut.close();
            
            DataSource fds = new ByteArrayDataSource(bos.toByteArray(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo("conso@bracongo.cd");
            helper.setSubject("Rapport Ventes ratées et Erreurs cumulées du mois");
            helper.setFrom("rdsid@bracongo.cd");
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setDataHandler(new DataHandler(fds));

            //messageBodyPart1.setFileName(fds.getName());
            messageBodyPart1.setFileName("Commandes.xlsx");
            Multipart mpart = new MimeMultipart();
            mpart.addBodyPart(messageBodyPart1);
            message.setContent(mpart);
            sender.send(message);
            byte[] result = bos.toByteArray();
            return result;
        } catch (IOException ex) {
            Logger.getLogger(CommandeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(CommandeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void produceRapportSheet(XSSFSheet rapportSheet, List<Commande> commandes, List<CommandeItem> commandeItems) {

        Set<String> productList = getProductList(commandeItems);
        int produitsSize = productList.size();
        int rowId = 0;
        int colId = 0;
        Cell cell;
        Row row = rapportSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("RAPPORT COMMANDES");
        cell.setCellStyle(myStyle);
        rapportSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 8 + produitsSize + 1 //last column  (0-based)
        ));

        rowId++;
        colId = 0;

        row = rapportSheet.createRow(rowId);
        cell = row.createCell(colId++);
        cell.setCellValue("DATE");
        cell.setCellStyle(grey);
        cell = row.createCell(colId++);
        cell.setCellValue("CLIENT");
        cell.setCellStyle(grey);
        cell = row.createCell(colId++);
        cell.setCellValue("CD");
        cell.setCellStyle(grey);
        cell = row.createCell(colId++);
        cell.setCellValue("CIRCUIT");
        cell.setCellStyle(grey);
        cell = row.createCell(colId++);
        cell.setCellValue("AGENT");
        cell.setCellStyle(grey);
        cell = row.createCell(colId++);
        cell.setCellValue("STATUT");
        cell.setCellStyle(grey);
        cell = row.createCell(colId++);
        cell.setCellValue("QTE");
        cell.setCellStyle(grey);
        cell = row.createCell(colId++);
        cell.setCellValue("PRIX");
        cell.setCellStyle(grey);
        for (String string : productList) {
            cell = row.createCell(colId++);
            cell.setCellValue(string);
            cell.setCellStyle(myStyle3);
        }
        rowId = 2;
        colId = 0;
        for (Commande commande : commandes) {
            row = rapportSheet.createRow(rowId);
            cell = row.createCell(colId++);
            cell.setCellValue(getStringFromDate(commande.getDateCommande()));
            cell = row.createCell(colId++);
            cell.setCellValue(commande.getClient().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(commande.getCd().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(commande.getCircuit().trim());
            cell = row.createCell(colId++);
            cell.setCellValue(commande.getUtilisateur().getNom());
            cell = row.createCell(colId++);
            cell.setCellValue(commande.getStatut());
            cell = row.createCell(colId++);
            cell.setCellValue(commande.getQuantite());
            cell = row.createCell(colId++);
            cell.setCellValue(commande.getPrixTotal());
            for (String string : productList) {
                int qte = getProductNumber(string, commande.getId(), commandeItems);
                cell = row.createCell(colId++);
                cell.setCellValue(qte == 0 ? "" : qte + "");
            }
            rowId++;
            colId = 0;
        }

        // TOTAL GENERAL
        row = rapportSheet.createRow(rowId);
        cell = row.createCell(colId);
        cell.setCellValue("Total Général");
        cell.setCellStyle(footer);
        rapportSheet.addMergedRegion(new CellRangeAddress(
                rowId, //first row (0-based)
                rowId, //last row  (0-based)
                colId, //first column (0-based)
                colId + 7 //last column  (0-based)
        ));

        colId = 8;
        for (String string : productList) {
            int total = getTotalOfProduit(string, commandeItems);
            cell = row.createCell(colId++);
            cell.setCellValue(total);
            cell.setCellStyle(footer);
        }

    }

    private Set<String> getProductList(List<CommandeItem> commandeItems) {
        Set<String> result = new HashSet<>();
        commandeItems.forEach((commandeItem) -> {
            result.add(commandeItem.getNomProduit());
        });
        return result;
    }

    private int getTotalOfProduit(String produit, List<CommandeItem> commandeItems) {
        int result = 0;
        result = commandeItems.stream().filter((commandeItem) -> (commandeItem.getNomProduit().equalsIgnoreCase(produit))).map((commandeItem) -> commandeItem.getQuantite()).reduce(result, Integer::sum);
        return result;
    }

    private int getProductNumber(String product, Long commandeId, List<CommandeItem> commandeItems) {
        for (CommandeItem commandeItem : commandeItems) {
            if ((commandeItem.getCommande().getId() == commandeId) && commandeItem.getNomProduit().equalsIgnoreCase(product)) {
                return commandeItem.getQuantite();
            }
        }
        return 0;
    }

    private String getStringFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);
        return cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR) + " " + String.format("%02d", hour) + ":" + String.format("%02d", minutes);
    }

    @Override
    public List<Commande> getAllCommandesBetweenDates(Date debut, Date fin) {
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal.setTime(debut);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal2.setTime(fin);
        cal2.set(Calendar.HOUR_OF_DAY, 23);
        List<Commande> commandes = commandeRepository.findAllByDateCommandeBetween(cal.getTime(), cal2.getTime());
        System.out.println("NOMBRE " + commandes.size());
        for (Commande commande : commandes) {
            System.out.println(commande);
        }
        return commandes;
    }

}
