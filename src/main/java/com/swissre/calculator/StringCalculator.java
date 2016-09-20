package com.swissre.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fredrikason on 20/09/2016.
 */
public class StringCalculator {

    public static int add(String numbers) {
        int result = 0;
        Lexer lexer = new Lexer(numbers);
        for (Token token : lexer.parse()) {
            if (TokenType.NUMBER.equals(token.type)) {
                int number = Integer.parseInt(token.value);
                if (number < 0)
                    throw new RuntimeException("Negative numbers are not supported");
                if (number <= 100)
                    result += number;
            }
        }
        return result;
    }

    public static enum TokenType {
        NUMBER("-?[0-9]+"),DELIMITER("[ ,/]+");

        private final String pattern;

        private TokenType(String pattern) {
            this.pattern = pattern;
        }
    }

    public static class Token {
        private TokenType type;
        private String value;

        public Token(TokenType type, String value) {
            this.type = type;
            this.value = value;
        }
    }

    public static class Lexer {
        private String input;

        public Lexer(String input){
            this.input = input;
        }

        public List<Token> parse() {
            List<Token> tokens = new ArrayList<Token>();

            StringBuffer buffer = new StringBuffer();
            for (TokenType type : TokenType.values()) {
                buffer.append(String.format("|(?<%s>%s)", type.name(), type.pattern));
            }

            if (input == null || "".equals(input)) {
                return tokens;
            }

            Pattern pattern = Pattern.compile(new String(buffer.substring(1)));

            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                for (TokenType type: TokenType.values()) {
                    if (matcher.group(type.name()) != null) {
                        tokens.add(new Token(type, matcher.group(type.name())));
                        break;
                    }
                }
            }

            return tokens;
        }
    }
}
