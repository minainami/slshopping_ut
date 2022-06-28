package com.example.category;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.example.entity.Category;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    /** モック化したクラス */
    @Mock
    private CategoryRepository mockCategoryRepository;
    
    /** テスト対象クラスにモックを注入 */
    @InjectMocks
    private CategoryService target;

    /**
    * テストデータの投入
    */
    @BeforeAll
    static void setUp(){
        // Oracleデータベース接続用ドライバクラスを指定
        IDatabaseConnection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Oracleデータベース接続コネクションに接続URL,ユーザーID,パスワードを指定
            Connection jdbcConnection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "SLSHOP_UT", "slshop");
            // 返却用データベースコネクションを取得
            connection = new DatabaseConnection(jdbcConnection, "SLSHOP_UT");
            // データベースに追加するデータファイルを指定
            IDataSet iDataset = new CsvDataSet(
                    new File(System.getProperty("user.dir")
                            + "\\src\\test\\resources\\testData"));
            // データベースの指定テーブルデータを、全データ削除後に、追加するデータファイルの内容に変更
            DatabaseOperation.CLEAN_INSERT.execute(connection, iDataset);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (connection != null) {
                try {
                    // データベース接続用コネクションをクローズ
                    connection.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }
   
    /**
    * 概要 カテゴリー名の入力チェック<br>
    * 条件 カテゴリー名が1文字の場合<br>
    * 結果 trueを返すこと
    */
    @Test
    void カテゴリー名が1文字の場合trueを返すこと() {

    }
    
    /**
     * 概要 カテゴリー名の入力チェック<br>
     * 条件 カテゴリー名が32文字の場合<br>
     * 結果 trueを返すこと
     */
    @Test
    void カテゴリー名が32文字の場合trueを返すこと() {

    }

    /**
    * 概要 カテゴリー名の入力チェック<br>
    * 条件 カテゴリー名が0文字の場合<br>
    * 結果 falseを返すこと
    */
    @Test
    void カテゴリー名が0文字の場合falseを返すこと() {

    }
    
    /**
    * 概要 カテゴリー名の入力チェック<br>
    * 条件 カテゴリー名が33文字の場合<br>
    * 結果 falseを返すこと
    */
    @Test
    void カテゴリー名が33文字の場合falseを返すこと() {

    }
    
    /**
    * 概要 カテゴリー名の重複チェック<br>
    * 条件 カテゴリー名が重複していない場合<br>
    * 結果 trueを返すこと
    */    
    @Test
    void カテゴリー名が重複していない場合trueを返すこと() {

    }
    
    /**
    * 概要 カテゴリー名の重複チェック<br>
    * 条件 カテゴリー名が重複する場合<br>
    * 結果 falseを返すこと
    */
    @Test
    void カテゴリー名が重複する場合falseを返すこと() {

    }

    /**
    * 概要 カテゴリー情報の取得<br>
    * 条件 指定したカテゴリーIDに対応するカテゴリー情報が存在する場合<br>
    * 結果 例外が発生しないこと
    */
    @Test
    void カテゴリー情報が存在する場合例外が発生しないこと() {

    }
    
    /**
    * 概要 カテゴリー情報の取得<br>
    * 条件 指定したカテゴリーIDに対応するカテゴリー情報が存在しない場合<br>
    * 結果 例外が発生すること
    */
    @Test
    void カテゴリー情報が存在しない場合例外が発生すること() {

    }
    
    /**
    * カテゴリー情報の取得処理の検証<br>
    * 条件 空のカテゴリー情報をスタブに設定する<br>
    * 結果 取得結果がスタブで設定したカテゴリー情報と等しいこと
    */
    @Test
    void カテゴリー情報の取得処理の検証() throws Exception {

    }
}
