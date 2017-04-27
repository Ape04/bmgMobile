package com.codename1.uikit.cleanmodern;

import com.codename1.util.StringUtil;
import java.util.List;

public class AI {
    private static AI instance = new AI();
    private static final String[] BAD_WORDS = {"fuck", "sex"};
    
    private static final String CANT_ABIDE_SUCH_LANGUAGE = "I can't abide such language... Clean up your act...";
    
    private static final String WHY_ARE_YOU_CONCERNED = "Why are you concerned about ";

    private static final String TOO_LITTLE_DATA_PLEASE_TELL_ME_MORE = "Too little data. Please tell me more...";
    
    public static String getResponse(String question) {
        return instance.getResponseToQuestion(question.toLowerCase());
    }

    private String getResponseToQuestion(String question) {
        if(has(question, BAD_WORDS)) {
            return CANT_ABIDE_SUCH_LANGUAGE;
        }
        
        if(question.startsWith("please")) {
            return "You don't have to be so polite";
        }
        
        if(question.startsWith("say ")) {
            return question.substring(4);
        }
        
        if(question.length() < 6) {
            return TOO_LITTLE_DATA_PLEASE_TELL_ME_MORE;
        }
        
        List<String> tokens = StringUtil.tokenize(question, " .,;\"':-?!-_");
        return WHY_ARE_YOU_CONCERNED + tokens.get(tokens.size() - 1);
    }
    
    private boolean has(String question, String[] words) {
        for(String s : words) {
            if(question.indexOf(s) > -1) {
                return true;
            }
        }
        return false;
    }
}
