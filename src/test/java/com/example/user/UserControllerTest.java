package com.example.user;

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

import com.example.entity.User;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    /** モック化したクラス */
    @Mock
    private UserService mockUserService;

    /** テスト対象クラスにモックを注入 */
    @InjectMocks
    private UserController target;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {

    }

    /**
     * 管理者一覧表示画面の検証
     */
    @Test
    void listUsersTest() throws Exception {

    }

    /**
     * 管理者新規登録画面の検証
     */
    @Test
    void newUserTest() throws Exception {

    }

    /**
     * 管理者登録・更新処理の検証
     */
    @Test
    void saveUserTest() throws Exception {

    }

    /**
     * 管理者詳細画面の検証
     */
    @Test
    void detailUserTest() throws Exception {

    }

    /**
     * 管理者編集画面の検証
     */
    @Test
    void editUserTest() throws Exception {

    }

    /**
     * 管理者削除の検証
     * 
     */
    @Test
    void deleteUserTest() throws Exception {

    }
}
