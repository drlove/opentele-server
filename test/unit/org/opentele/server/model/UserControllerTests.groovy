package org.opentele.server.model

import org.junit.*
import org.opentele.server.model.User;
import org.opentele.server.model.UserController;

import grails.test.mixin.*

@TestFor(UserController)
@Mock(User)
class UserControllerTests {
    def populateValidParams(params) {
        assert params != null
    }

    void testIndex() {
        controller.index()

        assert response.redirectedUrl == "/user/list"
    }

    void testList() {
        def model = controller.list()

        assert model.userInstanceList.size() == 0
        assert model.userInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.userInstance != null
    }

    void testSave() {
        controller.save()

        assert model.userInstance != null
        assert view == '/user/create'
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/user/list'
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/user/list'
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/user/list'
    }

    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/user/list'
    }
}
