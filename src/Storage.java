import java.io.FileReader;
import java.io.IOException;

public class Storage {
    private Language fromLanguage;
    private Language toLanguage;
    private String inputWord;

    public Storage(){
        fromLanguage = null;
    }

    public String find(String input){
        inputWord = input;
        inputWord = inputWord.trim();
        inputWord = inputWord.toLowerCase();

        defineLanguage(inputWord);

        int id = searchForID();
        return searchByID(id);

    }

    public void setToLanguage(Language toLanguage) {
        this.toLanguage = toLanguage;
    }

    private void defineLanguage(String word){
        if((word.charAt(0) >= 'a' && word.charAt(0) <= 'z')){
            fromLanguage = Language.English;
        }

        if((word.charAt(0) >= 'а' && word.charAt(0) <= 'я')){
            fromLanguage = Language.Russian;
        }
    }

    private int searchForID(){
        StringBuilder number = new StringBuilder();
        StringBuilder comparison = new StringBuilder();

        String path;

        switch (fromLanguage){
            case English:
                path = "/home/nazar/IdeaProjects/untitled7/words/eng.txt";
                break;
            case Russian:
                path = "/home/nazar/IdeaProjects/untitled7/words/rus.txt";
                break;
            case Albanian:
                path = "/home/nazar/IdeaProjects/untitled7/words/alb.txt";
                break;
            default:
                path = "";
                break;
        }


        try (FileReader reader = new FileReader(path)) {

            int c = reader.read();

            while (c != -1){
                while (Character.isDigit((char) c) || (char) c == ' '){
                    if(Character.isDigit((char) c)){
                        number.append((char) c);
                    }
                    c = reader.read();
                }

                while ((char) c != '\n'){
                    comparison.append((char) c);
                    c = reader.read();
                }

                if(comparison.toString().equals(inputWord)){
                    break;
                } else{
                    number = new StringBuilder();
                    comparison = new StringBuilder();
                    c = reader.read();
                }
            }

        }catch (IOException e) {
            // Print problem with file reading
        }

        if(number.toString().equals("")){
            return -1;
        }
        return Integer.parseInt(number.toString());
    }


    private String searchByID(int id) {
        StringBuilder outputWord = new StringBuilder();
        StringBuilder number = new StringBuilder();
        String path;

        switch (toLanguage){
            case English:
                path = "/home/nazar/IdeaProjects/untitled7/words/eng.txt";
                break;
            case Russian:
                path = "/home/nazar/IdeaProjects/untitled7/words/rus.txt";
                break;
            case Albanian:
                path = "/home/nazar/IdeaProjects/untitled7/words/alb.txt";
                break;
            default:
                path = "";
                break;
        }

        if(id == -1){
            return "Unknown word";
        }

        try(FileReader reader = new FileReader(path)){

            int c = reader.read();

            while (c != -1){
                while(Character.isDigit((char) c) || (char) c == ' '){
                    if(Character.isDigit((char) c)){
                        number.append((char) c);
                    }
                    c = reader.read();
                }

                if(Integer.parseInt(number.toString()) == id){
                    while((char) c != '\n'){
                        outputWord.append((char) c);
                        c = reader.read();
                    }
                    break;
                }else {
                    if((char) c == '\n') {
                        number = new StringBuilder();
                    }
                }

                c = reader.read();
            }
        }catch(IOException e){
            // Print problem with file reading
        }
        return outputWord.toString();
    }

}