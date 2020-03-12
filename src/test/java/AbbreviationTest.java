import org.testng.Assert;
import org.testng.annotations.Test;

public class AbbreviationTest {
    @Test
    public void oneNumber() {
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("0"), "0");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("9"), "9");
    }

    @Test
    public void oneLetter() {
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("а"), "А");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("я"), "Я");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("А"), "А");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Я"), "Я");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("анастасия"), "А");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("яблочный"), "Я");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Анастасия"), "А");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Яблочный"), "Я");
    }

    @Test
    public void fewNumbers() {
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("0 1 2 3 4 5 6 7 8 9"), "0123456789");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("01234 12345 23456 34567 45678 56789 67890 78901 89012 90123"), "0123456789");
    }

    @Test
    public void someWords() {
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("а р я"), "АРЯ");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Я Р А"), "ЯРА");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Я р А р"), "ЯРАР");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Автономный Удмуртскй Яр"), "АУЯ");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("яр удмуртский автономный"), "ЯУА");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Яр удмуртский автономный Округ"), "ЯУАО");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Яр удмуртский 1 автономный Округ"), "ЯУ1АО");
    }

    @Test
    public void ignoredWords() {
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("им с из"), "Недостаточно слов для формирования аббревиатуры! Предлоги и пробелы не обрабатываются.");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("для"), "Недостаточно слов для формирования аббревиатуры! Предлоги и пробелы не обрабатываются.");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("имени Петра дворец"), "ПД");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Петра дворец с"), "ПД");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Петровский и зимний дворец"), "ПЗД");
    }

    @Test
    public void spbWords() {
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Санкт-Петербург"), "СПб");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("САНКТ-ПЕТЕРБУРГСКИЙ"), "СПб");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("САНКТ-ПЕТЕРБУРГСКИЙ государственный институт"), "СПбГИ");
    }

    @Test
    public void emptyText() {
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation(""), "Вы ввели пустое значение!");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation(" "), "Недостаточно слов для формирования аббревиатуры! Предлоги и пробелы не обрабатываются.");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("   "), "Недостаточно слов для формирования аббревиатуры! Предлоги и пробелы не обрабатываются.");
    }

    @Test
    public void spaceText() {
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Аляска "), "А");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation(" Аляска"), "А");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Аляска  Штат"), "АШ");
    }

    @Test
    public void symbolsText() {
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("%"), "Обрабатываются только буквы кириллицы и числа!");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("% * / №"), "Обрабатываются только буквы кириллицы и числа!");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("% Аранжерея №"), "Обрабатываются только буквы кириллицы и числа!");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Аляскинский -университет"), "Обрабатываются только буквы кириллицы и числа!");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("-Аляскинский университет"), "Обрабатываются только буквы кириллицы и числа!");
    }

    @Test
    public void latinText() {
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("a"), "Обрабатываются только буквы кириллицы и числа!");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("A"), "Обрабатываются только буквы кириллицы и числа!");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("z"), "Обрабатываются только буквы кириллицы и числа!");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Z"), "Обрабатываются только буквы кириллицы и числа!");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("a g z"), "Обрабатываются только буквы кириллицы и числа!");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("A G Z"), "Обрабатываются только буквы кириллицы и числа!");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("A a Z z"), "Обрабатываются только буквы кириллицы и числа!");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Apple"), "Обрабатываются только буквы кириллицы и числа!");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("apple"), "Обрабатываются только буквы кириллицы и числа!");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("apple peach banana"), "Обрабатываются только буквы кириллицы и числа!");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Apple Peach Banana"), "Обрабатываются только буквы кириллицы и числа!");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Apple Университет"), "Обрабатываются только буквы кириллицы и числа!");
        Assert.assertEquals(AbbreviationBuilder.getAbbreviation("Университет PEACH"), "Обрабатываются только буквы кириллицы и числа!");
    }
}
