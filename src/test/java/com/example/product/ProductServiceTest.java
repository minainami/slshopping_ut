package com.example.product;

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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.example.entity.Product;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    /** モック化したクラス */
    @Mock
    private ProductRepository mockProductRepository;
    
    /** テスト対象クラスにモックを注入 */
    @InjectMocks
    private ProductService target;
    
    /**
    * テストデータの投入
    */
    @BeforeAll
    static void setUp(){

    }
    
    /**
    * 概要 商品名・商品説明のパラメーター化テスト<br>
    * 条件1 商品名が1文字かつ商品説明が1文字の場合<br>
    * 条件2 商品名が10文字かつ商品説明が50文字の場合<br>
    * 条件3 商品名が10文字かつ商品説明が1文字の場合<br>
    * 条件4 商品名が1文字かつ商品説明が50文字の場合<br>
    * 結果 trueを返すこと
    */
    @ParameterizedTest
    @CsvFileSource(resources = "paramtest_true.csv", numLinesToSkip = 1)
    void parameterTestTrue(String name, String description) {

    }

    /**
    * 概要 商品名・商品説明のパラメーター化テスト<br>
    * 条件1 商品名が0文字かつ商品説明が0文字の場合<br>
    * 条件2 商品名が11文字かつ商品説明が51文字の場合<br>
    * 条件3 商品名が10文字かつ商品説明が0文字の場合<br>
    * 条件4 商品名が0文字かつ商品説明が50文字の場合<br>
    * 条件5 商品名が10文字かつ商品説明が51文字の場合<br>
    * 条件6 商品名が11文字かつ商品説明が50文字の場合<br>
    * 結果 falseを返すこと
    */
    @ParameterizedTest
    @CsvFileSource(resources = "paramtest_false.csv", numLinesToSkip = 1)
    void parameterTestFalse(String name, String description) {

    }

    /**
    * 概要 商品名の重複チェック<br>
    * 条件 商品名が重複していない場合<br>
    * 結果 trueを返すこと
    */
    @Test
    void 商品名が重複していない場合trueを返すこと() {

    }
    /**
    * 概要 商品名の重複チェック<br>
    * 条件 商品名が重複する場合<br>
    * 結果 falseを返すこと
    */
    @Test
    void 商品名が重複する場合falseを返すこと() {

    }
    
    /**
    * 概要 商品情報の取得<br>
    * 条件 指定した商品IDに対応する商品情報が存在する場合<br>
    * 結果 例外が発生しないこと
    */
    @Test
    void 商品情報が存在する場合例外が発生しないこと() {

    }
    /**
    * 概要 商品情報の取得<br>
    * 条件 指定した商品IDに対応する商品情報が存在しない場合<br>
    * 結果 例外が発生すること
    */
    @Test
    void 商品情報が存在しない場合例外が発生すること() {

    }
    
    /**
    * 商品情報の取得処理の検証<br>
    * 条件 空の商品情報をスタブに設定する<br>
    * 結果 取得結果がスタブで設定した商品情報と等しいこと
    */
    @Test
    void 商品情報の取得処理の検証() throws Exception {

    }
}
