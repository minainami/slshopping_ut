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
    	Category category = new Category("あ");
    	assertThat(target.isValid(category)).isTrue();
    }

    /**
     * 概要 カテゴリー名の入力チェック<br>
     * 条件 カテゴリー名が32文字の場合<br>
     * 結果 trueを返すこと
     */
    @Test
    void カテゴリー名が32文字の場合trueを返すこと() {
    	//String categoryName = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    	//Category category = new Category(categoryName);
    	Category category = new Category(
			  "ああああああああああ"
			+ "ああああああああああ"
			+ "ああああああああああ"
			+ "ああ"
    	);
    	assertThat(target.isValid(category)).isTrue();
    }

    /**
    * 概要 カテゴリー名の入力チェック<br>
    * 条件 カテゴリー名が0文字の場合<br>
    * 結果 falseを返すこと
    */
    @Test
    void カテゴリー名が0文字の場合falseを返すこと() {
    	Category category = new Category("");
    	assertThat(target.isValid(category)).isFalse();
    }

    /**
    * 概要 カテゴリー名の入力チェック<br>
    * 条件 カテゴリー名が33文字の場合<br>
    * 結果 falseを返すこと
    */
    @Test
    void カテゴリー名が33文字の場合falseを返すこと() {
    	//String categoryName = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
    	//Category category = new Category(categoryName);
    	Category category = new Category(
				  "ああああああああああ"
				+ "ああああああああああ"
				+ "ああああああああああ"
				+ "あああ"
	    	);
    	assertThat(target.isValid(category)).isFalse();
    }

    /**
    * 概要 カテゴリー名の重複チェック<br>
    * 条件 カテゴリー名が重複していない場合<br>
    * 結果 trueを返すこと
    */
    @Test
    // ★★要復習

    void カテゴリー名が重複していない場合trueを返すこと() {
    	// カテゴリー名が重複していないブランド情報を作成
    	Category newCategory = new Category("ああああああ");

    	// スタブの設定
    	doReturn(null).when(this.mockCategoryRepository).findByName(anyString());

    	// 検証
    	assertThat(target.checkUnique(newCategory)).isTrue();
    }

    /**
    * 概要 カテゴリー名の重複チェック<br>
    * 条件 カテゴリー名が重複する場合<br>
    * 結果 falseを返すこと
    */
    @Test
    // ★★要復習
    void カテゴリー名が重複する場合falseを返すこと() {
    	// 準備 カテゴリー名が重複するブランド情報を作成
    	Category newCategory = new Category("カテゴリーA");

    	// スタブに設定するデータを作成
    	Category mockCategory = new Category();
    	mockCategory.setId(1L);
    	mockCategory.setName("カテゴリーA");

    	// スタブの設定
    	doReturn(mockCategory).when(this.mockCategoryRepository).findByName(newCategory.getName());

    	// 検証
    	assertThat(target.checkUnique(newCategory)).isFalse();
    }

    /**
    * 概要 カテゴリー情報の取得<br>
    * 条件 指定したカテゴリーIDに対応するカテゴリー情報が存在する場合<br>
    * 結果 例外が発生しないこと
    */
    @Test
    // ★★要復習
    void カテゴリー情報が存在する場合例外が発生しないこと() {
    	// 準備 テストデータに存在するID
    	Long id = 1L;

    	// スタブに設定するデータを作成
    	Long count = 1L;
    	Optional<Category> category = Optional.of(new Category());

    	// スタブの設定
    	doReturn(count).when(this.mockCategoryRepository).countById(id);
    	doReturn(category).when(this.mockCategoryRepository).findById(id);

    	// 検証
    	assertThatCode(() -> {
    		target.get(id);
    	}).doesNotThrowAnyException();
    }

    /**
    * 概要 カテゴリー情報の取得<br>
    * 条件 指定したカテゴリーIDに対応するカテゴリー情報が存在しない場合<br>
    * 結果 例外が発生すること
    */
    @Test
    // ★★要復習
    void カテゴリー情報が存在しない場合例外が発生すること() {
    	// 準備 テストデータに存在するID
    	Long id = 1000L;

    	// スタブの設定
    	doReturn(null).when(this.mockCategoryRepository).countById(id);

    	// 検証
    	assertThatThrownBy(() -> {
    		this.target.get(id);
    	})
    	.isInstanceOf(NotFoundException.class);

    }

    /**
    * カテゴリー情報の取得処理の検証<br>
    * 条件 空のカテゴリー情報をスタブに設定する<br>
    * 結果 取得結果がスタブで設定したカテゴリー情報と等しいこと
    */
    @Test
    // ★★要復習
    void カテゴリー情報の取得処理の検証() throws Exception {
    	// 準備 任意のID
    	Long id = 1L;

    	// スタブに設定するデータを作成
    	Long count = 1L;
    	Optional<Category> category = Optional.of(new Category());

    	// スタブの設定
    	doReturn(count).when(this.mockCategoryRepository).countById(id);
    	doReturn(category).when(this.mockCategoryRepository).findById(id);

    	// 検証
    	Category actual = this.target.get(id);
    	assertThat(actual).isEqualTo(category.get());
    }
}
