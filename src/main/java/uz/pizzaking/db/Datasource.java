package uz.pizzaking.db;

import uz.pizzaking.entity.Languages;
import uz.pizzaking.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Datasource {
    public static final Long ADMIN = 5699944692L;

    public static Map<Long, Integer> state = new HashMap<>();

    public static Map<Long, Languages> users = new HashMap<>();

    public static List<User> userList = new ArrayList<>();
}
