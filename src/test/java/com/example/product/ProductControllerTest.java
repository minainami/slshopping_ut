package com.example.product;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.brand.BrandService;
import com.example.category.CategoryService;
import com.example.entity.Product;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    /** モック化したクラス */
    @Mock
    private ProductService mockProductService;
    
    @Mock
    private BrandService mockBrandService;

    @Mock
    private CategoryService mockCategoryService;

    @Mock
    private ProductSaveHelper productSaveHelper;
    
    /** テスト対象クラスにモックを注入 */
    @InjectMocks
    private ProductController target;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {

    }

    /**
     * 商品一覧表示画面の検証
     */
    @Test
    void listProductsTest() throws Exception {

    }

    /**
     * 商品新規登録画面の検証
     */
    @Test
    void newProductTest() throws Exception {

    }

    /**
     * 商品登録・更新処理の検証
     */
    @Test
    void saveProductTest() throws Exception {

    }

    /**
     * 商品詳細画面の検証
     */
    @Test
    void detailProductTest() throws Exception {

    }

    /**
     * 商品編集画面の検証
     */
    @Test
    void editProductTest() throws Exception {

    }

    /**
     * 商品削除の検証
     */
    @Test
    void deleteProductTest() throws Exception {

    }
}
