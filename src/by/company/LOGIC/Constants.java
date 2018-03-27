package by.company.LOGIC;

public class Constants {
    public static final long KB = 1024;
    public static final long MB = 1024*1024;
    public static final long GB = 1024*1024*1024;
    public static final int MB_10= 10485760;

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
    public static final byte[] SALT = {-95, 65, -68, -50, 13, 29, 18, 91, -9, 100, 91, -17, -14, 45, 124, 79,
            -29, 70, -28, -80, 22, -57, -29, 23, -99, -6, 80, -36, 67, -114, -88, 47};

    public static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    public static final int ITERATION_COUNT = 65536;
    public static final int KEY_LENGTH = 256;
    public static final int NUM_OF_BYTES = 32;

}
