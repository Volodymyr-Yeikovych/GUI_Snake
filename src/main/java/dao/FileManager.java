package dao;

import model.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FileManager {

    private static final File DEFAULT_SAVE_PATH = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\bin\\");
    private static final File DEFAULT_FILENAME = new File("save.bin");

    static {
        String[] dirTree = DEFAULT_SAVE_PATH.toString().split("\\\\");
        for (int i = 0; i < dirTree.length; i++) {
            int elements = i + 1;
            StringBuilder path = new StringBuilder();
            for (int j = 0; j < elements; j++) {
                path.append(dirTree[j]).append("\\");
            }
            File dir = new File(path.toString());
            if (!dir.exists()) dir.mkdir();
        }

        File save = new File(DEFAULT_SAVE_PATH.getAbsolutePath() + "\\" + DEFAULT_FILENAME);
        try {
            if (!save.exists()) {
                save.createNewFile();
            }

            if (!DEFAULT_FILENAME.exists()) {
                DEFAULT_FILENAME.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToBin(Player thisPlayer) {
        byte[] byteSave = new byte[256];
        int nameLen = thisPlayer.getName().length();
        if (nameLen >= 64) {
            System.out.println("Cannot save, player name is too big.");
            return;
        }
        int counter = 1;
        for (byte b : thisPlayer.getName().getBytes()) {
            byteSave[counter] = b;
            counter++;
        }
        byteSave[0] = (byte) (counter - 1);
        int score = thisPlayer.getScore();
        byte first = (byte) (score >> 24);
        byte second = (byte) ((score >> 16) & 0xff);
        byte third = (byte) ((score >> 8) & 0xff);
        byte fours = (byte) (score & 0xff);
        byteSave[counter] = first;
        byteSave[counter + 1] = second;
        byteSave[counter + 2] = third;
        byteSave[counter + 3] = fours;
        byte[] finalSave = Arrays.copyOf(byteSave, counter + 4);
        System.out.println(Arrays.toString(finalSave));
        writeBytesToFile(finalSave);
    }

    private void writeBytesToFile(byte[] finalSave) {
        boolean append = true;
        try (FileOutputStream fos = new FileOutputStream(DEFAULT_SAVE_PATH.getAbsolutePath() + "\\" + DEFAULT_FILENAME, append)) {
            fos.write(finalSave);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Player> retrieveFromBin() {
        List<Player> playerList = new CopyOnWriteArrayList<>();
        byte[] save = readBytesFromFile();
        int i = 0;
        while (save.length > i) {
            int byteNameLen = save[i];
            i++;
            byte[] nameBytes = Arrays.copyOfRange(save, i, i + byteNameLen);
            String name = new String(nameBytes);
            i += byteNameLen;
            byte[] scoreBytes = Arrays.copyOfRange(save, i, i + 4);
            ByteBuffer buffer = ByteBuffer.allocate(4).put(scoreBytes);
            int score = buffer.getInt(0);
            System.out.println("Name(" + name + ") Score(" + score + ")");
            i += 4;
            playerList.add(new Player(name, score));
        }
        return playerList;
    }

    private byte[] readBytesFromFile() {
        try (FileInputStream fis = new FileInputStream(DEFAULT_SAVE_PATH.getAbsolutePath() + "\\" + DEFAULT_FILENAME)) {
            return fis.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
