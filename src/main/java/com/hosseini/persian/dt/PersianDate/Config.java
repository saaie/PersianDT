package com.hosseini.persian.dt.PersianDate;


/*
 * Copyright (C) 2015 Abbashosseini
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @since 3/8/16.
 */

public class Config implements FindIt {

    /**
     * assign constructor paratamter with this variable
     **/
    private final String dateContainStringObject, sentence;

    /**
     * CallBack Pattern
     * <p>
     * this CallBack Here can you access it from subClasses
     * no need to create one any more nedd it use it
     * </p>
     */

    private final class DateHolder{


        private final Date dateContainDateObject;

        DateHolder(String date) throws ParseException {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            dateContainDateObject = dateFormat.parse(date);
        }

        public Date getDate(){
            return dateContainDateObject;
        }

    }

    private DateHolder holder;
    private final Logger logger = Logger.getLogger(getClass().getSimpleName());

    Config(String date, String sentence) {
        this.dateContainStringObject = date;
        this.sentence = sentence;

        try {
            holder = new DateHolder(this.dateContainStringObject);
        } catch (ParseException e) {
            logger.warning("maybe you date not really have correct format !!");
        }
    }

    /**
     * Getter for grap date from constructor
     *
     * @return A Date
     */
    public Date getDateContaindateObject() {
        return holder.getDate();
    }

    /**
     * Getter for grap date from Constructor
     *
     * @return A String
     */
    public String getDateContainStringObject() {
        return dateContainStringObject;
    }

    /**
     * @return A String
     */
    public String getSentence() {
        return sentence;
    }


    /** {@inheritDoc} */

    /**
     * this method will find place for your date in paragraph or sentence
     *
     * @param date will we have to pass the date from subclass
     *             and find place and put it there
     * @return a String
     */

    @Override
    public String Location(String date) {

        Pattern pattern = Pattern.compile("\\{DATE\\}");
        Matcher matcher = pattern.matcher(getSentence());

        if (matcher.find())
            return matcher.replaceAll(date);
        else
            return date;
    }
}
