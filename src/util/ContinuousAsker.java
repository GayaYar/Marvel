package util;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class ContinuousAsker {

    /**
     * Asks the "question" until the response passes the "isValid" test.
     * If the response is invalid, print the "invalidMessage" and continues asking.
     * When response is valid, returns the response.
     *
     * @param question
     * @param getAnswer - the method to get an answer from user
     * @param isValid
     * @param invalidMessage
     * @return the valid response
     * @param <T>
     */
    public static <T> T ask(String question, Supplier<T> getAnswer, Predicate<T> isValid, String invalidMessage) {
        boolean valid = false;
        T answer = null;
        while(!valid) {
            System.out.println(question);
            answer = getAnswer.get();
            if(isValid.test(answer)) {
                valid = true;
            }else {
                System.out.println(invalidMessage);
            }
        }
        return answer;
    }
}
