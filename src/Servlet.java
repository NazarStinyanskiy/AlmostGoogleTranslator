import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/translate")
public class Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String word = req.getParameter("field");

        Storage storage = new Storage();
        setLanguage(storage, req.getParameter("language"));

        String translate = storage.find(word);

        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.println(translate);
    }

    private void setLanguage(Storage storage, String lang){
        switch (lang){
            case "eng":
                storage.setToLanguage(Language.English);
                break;
            case "rus":
                storage.setToLanguage(Language.Russian);
                break;
            case "alb":
                storage.setToLanguage(Language.Albanian);
        }
    }
}