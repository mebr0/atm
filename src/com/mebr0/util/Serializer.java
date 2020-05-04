package com.mebr0.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for general java serialization
 * Files for saving stored in {@link #PREFIX} directory
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Serializer {

    private static final String PREFIX = "db/";

    private static final Logger LOG = Logger.getInstance();

    public static boolean serialize(String file, Object object) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(PREFIX + file))) {
            stream.writeObject(object);

            stream.flush();

            return true;
        }
        catch (FileNotFoundException e) {
            LOG.error(PREFIX + file + " not found");
        }
        catch (IOException e) {
            LOG.error(PREFIX + file + " io exception");
        }

        return false;
    }

    public static <T> T deserialize(String file, Class<T> clazz) {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(PREFIX + file))) {
            return (T) stream.readObject();
        }
        catch (ClassNotFoundException e) {
            LOG.error(PREFIX + file + " class not found");
        }
        catch (FileNotFoundException e) {
            LOG.error(PREFIX + file + " not found");
        }
        catch (IOException e) {
            LOG.error(PREFIX + file + " io exception");
        }

        return null;
    }

    public static <T> List<T> deserializeList(String file, Class<T> clazz) {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(PREFIX + file))) {
            return (ArrayList<T>) stream.readObject();
        }
        catch (ClassNotFoundException e) {
            LOG.error(PREFIX + file + " class not found");
        }
        catch (FileNotFoundException e) {
            LOG.error(PREFIX + file + " not found");
        }
        catch (IOException e) {
            LOG.error(PREFIX + file + " io exception");
        }

        return null;
    }
}
