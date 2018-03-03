package by.company.LOGIC;

public class Constants {
    public static final long KB = 1024;
    public static final long MB = 1024*1024;
    public static final long GB = 1024*1024*1024;

    public  static final String DOCUMENTS = new String("DOCUMENTS");
    public static final String BOOK = new String("BOOK");
    public static final String VIDEO  = new String("VIDEO");
    public static final String AUDIO = new String("AUDIO");

    public static final String[] DOCUMENTS_EXTENSIONS = {"*.docx", "*.doc", "*.ppt", ".pptx", "*.xlsx"};
    public static final String[] BOOK_EXTENSIONS = {"*.pdf"};
    public static final String[] VIDEO_EXTENSIONS = {"*.mkv", "*.avi", "*.mp4"};
    public static final String[] AUDIO_EXTENSIONS = {"*.mp3", "*.wav"};
    public static final String[] ALL_EXTENSIONS = {"*.docx", "*.doc", "*.ppt", ".pptx", "*.xlsx", "*.pdf", "*.mkv",
            "*.avi", "*.mp4", "*.mp3", "*.wav"};
}
