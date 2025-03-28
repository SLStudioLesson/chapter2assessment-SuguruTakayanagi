package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RecipeFileHandler {
    private String filePath;

    public RecipeFileHandler() {
        filePath = "app/src/main/resources/recipes.txt";
    }

    public RecipeFileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 設問1: 一覧表示機能
     * recipes.txtからレシピデータを読み込み、それをリスト形式で返します。 <br>
     * IOExceptionが発生したときは<i>Error reading file: 例外のメッセージ</i>とコンソールに表示します。
     *
     * @return レシピデータ
     */
    public ArrayList<String> readRecipes() {
        // recipe.txtを読み込み、内容を1行ごとにlineに保存して、リストに格納する。
        // このメソッドではリスト形式で返すようになっているので、最後にmenusリストを返す。
        ArrayList<String> menus = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            //内容を1行ごとにlineに格納する
            while ((line = reader.readLine()) != null) {
                //lineに保存した1行について(",")で区切ってリストに格納する
                menus.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
        //最後にリストのmenusを返す。
        return menus;
    }

    /**
     * 設問2: 新規登録機能
     * 新しいレシピをrecipes.txtに追加します。<br>
     * レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
     *
     * @param recipeName レシピ名
     * @param ingredients 材料名
     */
     //
    public void addRecipe(String recipeName, String ingredients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String content = recipeName + "," + ingredients;
            writer.newLine();
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
