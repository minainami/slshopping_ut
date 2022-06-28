package com.example.brand;

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

import com.example.entity.Brand;

/*
 * @SpringBootTest
 * 簡易版のテストはこのアノテーションを使う
 * @ExtendWith(MockitoExtension.class)と一緒には使えないためどちらか一方を利用する形となる
 */
@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    /** モック化したクラス */
    @Mock
    private BrandRepository mockBrandRepository;
    
    /** テスト対象クラスにモックを注入 */
    @InjectMocks
    private BrandService target;
    
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
            // testDataディレクトリ配下のファイルはすべて対象になる
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
    * 概要 ブランド名の入力チェック<br>
    * 条件 ブランド名が1文字の場合<br>
    * 結果 trueを返すこと
    */
    @Test
    void ブランド名が1文字の場合trueを返すこと() {
        Brand brand = new Brand("あ");
        assertThat(target.isValid(brand)).isTrue();
    }
    
    /**
     * 概要 ブランド名の入力チェック<br>
     * 条件 ブランド名が10文字の場合<br>
     * 結果 trueを返すこと
     */
    @Test
    void ブランド名が10文字の場合trueを返すこと() {
        Brand brand = new Brand("ああああああああああ");

        /* Lesson02 タスク -初級編- 課題1 */
        // 検証処理
    }
    
    /**
    * 概要 ブランド名の重複チェック<br>
    * 条件 ブランド名が重複していない場合<br>
    * 結果 trueを返すこと
    */
    @Test
    void ブランド名が重複していない場合trueを返すこと() {
        // ブランド名が重複していないブランド情報を作成
        Brand newBrand = new Brand("あいうえお");
        
        // スタブの設定
        doReturn(null).when(this.mockBrandRepository).findByName(anyString());
        
        /* Lesson02 タスク -初級編- 課題2 */
        // 検証処理
    }

    /**
    * 概要 ブランド情報の取得<br>
    * 条件 指定したブランドIDに対応するブランド情報が存在しない場合<br>
    * 結果 例外が発生すること
    */
    @Test
    void ブランド情報が存在しない場合例外が発生すること() {
        // 準備 テストデータに存在しないID
        Long id = 1000L;

        //スタブの設定
        doReturn(null).when(this.mockBrandRepository).countById(id);
        
        /* Lesson02 タスク -初級編- 課題3 */
        // 検証処理
    }
}