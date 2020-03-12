import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AbbreviationBuilder {
    static String[] ignoredWords = {"им", "им.", "имени", "и", "в", "без", "до", "из", "к", "на", "по", "о", "от", "перед", "при", "через", "с", "у", "за", "над", "об", "под", "про", "для"};
    static Set<String> setIgnoredWords;

    static {
        setIgnoredWords = new HashSet<String>(Arrays.asList(ignoredWords));
    }

    public static String getAbbreviation(String text) {
        if (text.equals("")) {
            return "Вы ввели пустое значение!";
        }

        String output = "";

        String[] textSplit = text.split(" ");

        for (int i = 0; i < textSplit.length; i++) {
            String word = textSplit[i].toUpperCase();
            if (word.startsWith("САНКТ-ПЕТЕРБУРГ")) {
                output += "СПБ";
                continue;
            } else if (isIgnoredWord(word)) {
                continue;
            }

            if (!word.equals("")) {
                String firstLetter = String.valueOf(word.charAt(0));
                if (firstLetter.matches("[а-яА-Я0-9]")) {
                    output += firstLetter;
                } else {
                    output = "Обрабатываются только буквы кириллицы и числа!";
                    break;
                }
            }
        }

        if (output.equals("")) {
            output = "Недостаточно слов для формирования аббревиатуры! Предлоги и пробелы не обрабатываются.";
        }

        return output;
    }

    private static boolean isIgnoredWord(String word) {
        return setIgnoredWords.contains(word.toLowerCase());
    }
}
