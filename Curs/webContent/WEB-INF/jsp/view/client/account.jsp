<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/base.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <title>
    <fmt:message key='cabinet.info' />
  </title>
  <body>
    <template:main>
      <jsp:body>
        <div class="col s12">
        <div class="row container">
        <h2 class="red-text">
          <fmt:message key='cabinet.hello' />
          ${user.name}!
        </h2>
            <div id="info" class="col s12">
              <h5 class="right">
                <fmt:message key='cabinet.manage_info' />
              </h5>
              <br><br>
              <table>
                <tr>
                  <td>
                    <b><fmt:message key='cabinet.name' /></b> <span id = "name">${user.name}</span>
                  </td>
                  <td>
                    <a class="waves-effect waves btn white amber-text" data-target="change-name-modal"
                      onclick="openNameModal()"><i class="material-icons left">edit</i></a>
                  </td>
                </tr>
                <tr>
                  <td>
                    <b><fmt:message key='cabinet.phone' /></b> <span id = "phone">${user.phone}</span>
                  </td>
                  <td>
                    <a class="waves-effect waves btn white amber-text" data-target="change-phone-modal"
                      onclick="openPhoneModal()"><i class="material-icons left">edit</i></a>
                  <td>
                </tr>
                <tr>
                  <td>
                    <b><fmt:message key='cabinet.email' /></b> <span id = "email">${user.email}</span>
                  </td>
                  <td>
                    <a class="waves-effect waves btn white amber-text" data-target="change-email-modal"
                      onclick="openEmailModal()"><i class="material-icons left">edit</i></a>
                  </td>
                </tr>
                <tr>
                  <td><b><fmt:message key='cabinet.password' /></b></td>
                  <td>
                    <a class="waves-effect waves btn white amber-text" data-target="change-pass-modal"
                      onclick="openPassModal()"><i class="material-icons left">edit</i></a>
                  </td>
                </tr>
              </table>
              
              <div id="change-name-modal" class="modal">
                <div class="modal-content">
                  <h4>
                    <fmt:message key='cabinet.change_name' />
                  </h4>
                  <form id="change-name-form" name="change-name-form">
                    <div class="input-field">
                      <i class="material-icons prefix">account_circle</i> <input
                        id="change-name" name="inputName" type="text" class="validate"
                        pattern="^[a-zA-Zа-яА-ЯїЇіІєЄ][a-zA-Zа-яА-ЯїЇіІєЄ `'-]{1,20}$">
                      <label
                        data-error="Enter valid name. Use only сyrillic or roman alphabet"
                        for="change-name">
                        <fmt:message key='sign_up.name' />
                      </label>
                      <span
                        class="error" id="name-error"> </span>
                    </div>
                    <div class="input-field ">
                      <i class="material-icons prefix">lock_outline</i> <input
                        id="change-name-password" name="password" type="password"
                        class="validate" pattern=".{6,25}"> 
                      <label
                        data-error="Your password must contain 6-25 symbols"
                        for="change-name-password">
                        <fmt:message
                          key='sign_up.password' />
                      </label>
                    </div>
                    <div>
                      <span class="error" id="change-name-form-error"> </span>
                    </div>
                    <a class="waves-effect waves-light btn amber"
                      onclick="changeName()"><i class="material-icons left">done</i>Ok</a> 
                    <a
                      class="waves-effect waves-light btn white amber-text" onclick="closeChangeModal()">
                      <i
                        class="material-icons left">cancel</i>
                      <fmt:message key='sign_up.cancel' />
                    </a>
                  </form>
                </div>
              </div>
              
              <div id="change-pass-modal" class="modal">
                <div class="modal-content">
                  <h4>
                    <fmt:message key='cabinet.change_pass' />
                  </h4>
                  <form id="change-pass-form" name="change-pass-form">
                    <div class="input-field ">
                      <i class="material-icons prefix">lock_outline</i> <input
                        id="old-pass" name="password" type="password"
                        class="validate" pattern=".{6,25}"> 
                      <label
                        data-error="Your password must contain 6-25 symbols"
                        for="old-pass">
                        <fmt:message
                          key='cabinet.old_pass' />
                      </label>
                    </div>
                    <div class="input-field ">
                      <i class="material-icons prefix">lock_outline</i> <input
                        id="new-pass" name="password" type="password"
                        class="validate" pattern=".{6,25}"> 
                      <label
                        data-error="Your password must contain 6-25 symbols"
                        for="new-pass">
                        <fmt:message
                          key='cabinet.new_pass' />
                      </label>
                    </div>
                    <div class="input-field ">
                      <i class="material-icons prefix">lock_outline</i> <input
                        id="conf-new-pass" name="password" type="password"
                        class="validate" pattern=".{6,25}"> 
                      <label
                        data-error="Your password must contain 6-25 symbols"
                        for="conf-new-pass">
                        <fmt:message
                          key='cabinet.new_pass_conf' />
                      </label>
                    </div>
                    <div>
                      <span class="error" id="change-pass-form-error"> </span>
                    </div>
                    <a class="waves-effect waves-light btn amber"
                      onclick="changePass()"><i class="material-icons left">done</i>Ok</a> 
                    <a
                      class="waves-effect waves-light btn white amber-text" onclick="closeChangeModal()">
                      <i
                        class="material-icons left">cancel</i>
                      <fmt:message key='sign_up.cancel' />
                    </a>
                  </form>
                </div>
              </div>
              
              <div id="change-phone-modal" class="modal">
                <div class="modal-content">
                  <h4>
                    <fmt:message key='cabinet.change_phone' />
                  </h4>
                  <form id="change-phone-form" name="change-phone-form">
                    <div class="input-field">
                      <i class="material-icons prefix">phone</i> <input
                        id="change-phone" name="inputPhone" type="text"
                        class="validate" pattern="[+]?[0-9 -]{6,17}"> 
                      <label
                        data-error="Enter valid phone. Example: +38 050 111 22 33"
                        for="change-phone">
                        <fmt:message key='sign_up.phone' />
                      </label>
                      <span class="error" id="phone-error"> </span>
                    </div>
                    <div class="input-field ">
                      <i class="material-icons prefix">lock_outline</i> <input
                        id="change-phone-password" name="password" type="password"
                        class="validate" pattern=".{6,25}"> 
                      <label
                        data-error="Your password must contain 6-25 symbols"
                        for="change-phone-password">
                        <fmt:message
                          key='sign_up.password' />
                      </label>
                    </div>
                    <div>
                      <span class="error" id="change-phone-form-error"> </span>
                    </div>
                    <a class="waves-effect waves-light btn amber"
                      onclick="changePhone()"><i class="material-icons left">done</i>Ok</a> 
                    <a
                      class="waves-effect waves-light btn white amber-text" onclick="closeChangeModal()">
                      <i
                        class="material-icons left">cancel</i>
                      <fmt:message key='sign_up.cancel' />
                    </a>
                  </form>
                </div>
              </div>
              
              <div id="change-email-modal" class="modal">
                <div class="modal-content">
                  <h4>
                    <fmt:message key='cabinet.change_email' />
                  </h4>
                  <form id="change-email-form" name="change-email-form">
                    <div class="input-field">
                      <i class="material-icons prefix">email</i> <input
                        id="change-email" name="inputEmail" type="text"
                        pattern="[a-zA-Z0-9._-]*@[a-zA-Z0-9]+\.[a-zA-Z0-9]+(.[a-zA-Z0-9]+)?"
                        class="validate"> 
                      <label data-error="Enter valid email"
                        for="change-login">
                        <fmt:message key='sign_up.email' />
                      </label>
                      <span class="error" id="email-error"> </span>
                    </div>
                    <div class="input-field ">
                      <i class="material-icons prefix">lock_outline</i> <input
                        id="change-email-password" name="password" type="password"
                        class="validate" pattern=".{6,25}"> 
                      <label
                        data-error="Your password must contain 6-25 symbols"
                        for="change-email-password">
                        <fmt:message
                          key='sign_up.password' />
                      </label>
                    </div>
                    <div>
                      <span class="error" id="change-email-form-error"> </span>
                    </div>
                    <a class="waves-effect waves-light btn amber"
                      onclick="changeEmail()"><i class="material-icons left">done</i>Ok</a> 
                    <a
                      class="waves-effect waves-light btn white amber-text" onclick="closeChangeModal()">
                      <i
                        class="material-icons left">cancel</i>
                      <fmt:message key='sign_up.cancel' />
                    </a>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </jsp:body>
    </template:main>
  </body>
</html>