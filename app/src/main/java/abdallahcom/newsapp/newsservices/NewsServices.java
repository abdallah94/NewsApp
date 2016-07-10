package abdallahcom.newsapp.newsservices;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import abdallahcom.newsapp.Constants;
import abdallahcom.newsapp.utilities.NewsArticle;

/**
 * Created by Bashar on 7/10/2016.
 */
public class NewsServices {

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("getBmpFromUrl error: ", e.getMessage().toString());
            return null;
        }
    }

    public List parse(String link) throws XmlPullParserException, IOException {
        InputStream in;
        URL url = new URL(link);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String xmlData;
        try {
            in = new BufferedInputStream(urlConnection.getInputStream());
            byte buffer[] = new byte[4096];

            int count;
            xmlData = "";
            while ((count = in.read(buffer)) != -1) {
                xmlData += new String(buffer, 0, count);
            }
        } finally {
            urlConnection.disconnect();
        }
        Log.d(Constants.TAG, " Data: " + xmlData);

        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List entries = new ArrayList();

        parser.require(XmlPullParser.START_TAG, Constants.ns, "feed");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("item")) {
                entries.add(readEntry(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private void skip(XmlPullParser parser) {

    }

    private NewsArticle readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, Constants.ns, "item");
        String title = null;
        String description = null;
        String link = null;
        String pubDate = null;
        Bitmap image = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("title")) {
                title = readTitle(parser);
            } else if (name.equals("description")) {
                description = readSummary(parser);
            } else if (name.equals("link")) {
                link = readLink(parser);
            } else if (name.equals("pubDate")) {
                pubDate = readPubDate(parser);
            } else if (name.equals("media:thumbnail")) {
                image = readImage(parser);
            } else {
                skip(parser);
            }
        }
        return new NewsArticle(title, description, link, pubDate, image);
    }

    private Bitmap readImage(XmlPullParser parser) throws IOException, XmlPullParserException {
        String url = "";
        parser.require(XmlPullParser.START_TAG, url, "media:thumbnail");
        url = parser.getAttributeValue(null, "url");
        parser.require(XmlPullParser.END_TAG, url, "media:thumbnail");
        return getBitmapFromURL(url);
    }

    // Processes title tags in the feed.
    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, Constants.ns, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, Constants.ns, "title");
        return title;
    }

    // Processes summary tags in the feed.
    private String readSummary(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, Constants.ns, "description");
        String description = readText(parser);
        parser.require(XmlPullParser.END_TAG, Constants.ns, "description");
        return description;
    }

    // Processes link tags in the feed.
    private String readLink(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, Constants.ns, "link");
        String link = readText(parser);
        parser.require(XmlPullParser.END_TAG, Constants.ns, "link");
        return link;
    }

    // Processes pubDate tags in the feed.
    private String readPubDate(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, Constants.ns, "pubDate");
        String pubDate = readText(parser);
        parser.require(XmlPullParser.END_TAG, Constants.ns, "pubDate");
        return pubDate;
    }


    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

}
