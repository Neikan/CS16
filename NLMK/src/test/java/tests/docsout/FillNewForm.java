package tests.docsout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import model.DocOutboundData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.TestBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class FillNewForm extends TestBase {

  @DataProvider
  public Iterator<Object[]> validDocsOutboundFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<DocOutboundData> docsOutbound = gson.fromJson(json, new TypeToken<List<DocOutboundData>>() {
      }.getType()); // List<DocOutboundData>.class
      return docsOutbound.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validDocsOutboundFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/DocOutBound/DataGenerator/data.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(DocOutboundData.class);
      List<DocOutboundData> docsOutbound = (List<DocOutboundData>) xstream.fromXML(xml);
      return docsOutbound.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }


  @Test(dataProvider = "validDocsOutboundFromXml")
  public void fillNewForm(DocOutboundData docOutbound) {
    app.session().loginAuthor();
    app.goTo().favorites("Исходящие документы");
    app.docs().gotoNewPage();
    app.docsout().fillForm(docOutbound);
    app.docsout().confirmAddDoc();
    app.docsout().initModification(docOutbound);
    app.docsout().getTitleOut(docOutbound);
    app.docsout().attachFiles(docOutbound);
    app.docsout().startWorkflow();
    app.goTo().main();
    app.session().logoff();
    app.session().loginController();
    app.docsout().openTask(docOutbound);
    app.docs().taskAccept();
    app.docs().taskApprove();
    app.session().logoff();
    app.session().loginHeadOfAuthor();
    app.docsout().openTask(docOutbound);
    app.docs().taskForRework();
  }
}
