package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import data.RecipeFileHandler;

public class RecipeUI {
    RecipeFileHandler handle = new RecipeFileHandler();
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 設問1: 一覧表示機能
                        this.displayRecipes();
                        break;
                    case "2":
                        // 設問2: 新規登録機能
                        this.addNewRecipe();
                        break;
                    case "3":
                        // 設問3: 検索機能
                        this.searchRecipe();
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    private void displayRecipes() {
        //readRecipes()メソッドを実行して、返されたリストをここで新規に作成したリストに格納する。
        //そのリストの1番最初の要素がレシピのタイトル、2番目以降の要素が材料として出力する。
        //あとは、READMEの出力例に従って、表示形式を整える。

        ArrayList<String> recipeData = new ArrayList<>();
        recipeData = handle.readRecipes();
        if (recipeData != null && !(recipeData.isEmpty())) {
                System.out.println("Recipes:");
                System.out.println("-----------------------------------");
                for (String recipe : recipeData) {
                    String[] list = recipe.split(",");
                    if (list != null) {
                        System.out.println("Recipe Name: " + list[0]);
                        System.out.print("Main Ingredients: ");
                        for (int i = 1; i < list.length -1; i++) {
                            System.out.print(list[i] + ",");
                        }
                        //材料の末尾には(",")が不要なので、下記で出力する。
                        System.out.print(list[list.length -1]);
                        System.out.println();
                        System.out.println("-----------------------------------");
                    }
                }
                //下記else{}、レシピのテキストファイルが空の場合に出力する。
            } else {
                System.out.println("No recipes available.");
            }
    }
    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void addNewRecipe() throws IOException {
        System.out.print("Enter recipe name: ");
        String recipeName = reader.readLine();

        System.out.println("Enter main ingredients (comma separated): ");
        String ingredients = reader.readLine();

        handle.addRecipe(recipeName, ingredients);

        System.out.println("Recipe added successfully.");
    }

    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void searchRecipe() throws IOException {

    }

}
