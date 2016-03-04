package com.vastikaEIS.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vastikaEIS.domain.Permissions;
import com.vastikaEIS.domain.RolePermissions;
import com.vastikaEIS.domain.Roles;
import com.vastikaEIS.dto.response.JsonMap;
import com.vastikaEIS.dto.response.PermissionsJson;
import com.vastikaEIS.dto.response.RoleJson;
import com.vastikaEIS.service.RoleService;

@RestController
@RequestMapping("/api")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/roles", method = RequestMethod.POST, headers = "Accept=Application/json")
	public JsonMap addRole(@Valid @RequestBody Roles roles, BindingResult result) throws ParseException,
			InterruptedException {
		Thread.sleep(2000);
		JsonMap jsonResponseMap = new JsonMap();
		if (result.hasErrors()) {
			jsonResponseMap.setValidationFailedStatus("validationFailureStatus");
			jsonResponseMap.setMessages("validationFailure");
			return jsonResponseMap;
		}
		long id = roleService.addRoles(roles);
		if (id > 0) {
			jsonResponseMap.setId(id);
			jsonResponseMap.setMessages("roleAddedSuccess");
			jsonResponseMap.setSuccessStatus();
		} else {
			jsonResponseMap.setMessages("roleAddFailed");
			jsonResponseMap.setFailedStatus();
		}

		return jsonResponseMap;
	}

	@RequestMapping(value = "/roles", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<RoleJson> listAll(Roles roles) throws ParseException {
		List<Roles> role = roleService.listAll();
		List<RoleJson> jsonRoles = new ArrayList<>();
		for (Roles rl : role) {
			RoleJson roleJson = new RoleJson();
			roleJson.setId(rl.getId());
			roleJson.setName(rl.getName());
			jsonRoles.add(roleJson);
		}
		return jsonRoles;
	}

	@RequestMapping(value = "/roles/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public RoleJson getById(@PathVariable long id) throws ParseException {
		Roles rl = roleService.getRoleById(id);
		RoleJson rolesjson = new RoleJson();
		rolesjson.setId(rl.getId());
		rolesjson.setName(rl.getName());

		List<RolePermissions> rolePermissions = roleService.getRoleMembersByRoleId(id);
		List<Long> jsonPermissions = new ArrayList<Long>();
		for (RolePermissions rp : rolePermissions) {
			jsonPermissions.add(rp.getPermissions().getId());
		}
		rolesjson.setPermissions(jsonPermissions);

		return rolesjson;
	}

	@RequestMapping(value = "/roles/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public JsonMap editById(@Valid @PathVariable long id, @Valid @RequestBody Roles roles, BindingResult result)
			throws ParseException {
		Roles rl = roleService.getRoleById(id);
		JsonMap jsonResponseMap = new JsonMap();
		if (rl == null) {
			jsonResponseMap.setFailedStatus();
			jsonResponseMap.setMessages("RoleNotFound");
			return jsonResponseMap;
		}
		roles.setId(id);
		if (result.hasErrors()) {
			jsonResponseMap.setValidationFailedStatus("validationFailure");
			jsonResponseMap.setMessages("ValidationFailure");
			return jsonResponseMap;
		}
		long typeId = roleService.addRoles(roles);
		if (typeId > 0) {
			jsonResponseMap.setMessages("RoleUpdateSuccess");
			jsonResponseMap.setSuccessStatus();
		} else {
			jsonResponseMap.setMessages("RoleUpdateFailed");
			jsonResponseMap.setFailedStatus();
		}
		return jsonResponseMap;
	}

	@RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public JsonMap deleteById(@PathVariable long id) throws ParseException {
		Roles rl = roleService.getRoleById(id);
		JsonMap jsonResponseMap = new JsonMap();
		if (rl == null) {
			jsonResponseMap.setFailedStatus();
			jsonResponseMap.setMessages("RoleNotFound");
			return jsonResponseMap;
		}

		if (roleService.deleteRole(id)) {
			jsonResponseMap.setMessages("RoleDeleteSuccess");
			jsonResponseMap.setStatus("SuccessStatus");
		} else {
			jsonResponseMap.setMessages("RoleDeleteFailed");
			jsonResponseMap.setFailedStatus();
		}
		return jsonResponseMap;
	}

	@RequestMapping(value = "/permissions", method = RequestMethod.GET)
	public List<PermissionsJson> listAllPermissions(Permissions permission) throws ParseException {
		List<Permissions> permissions = roleService.listAllPermissions();
		List<PermissionsJson> jsonPermissions = new ArrayList<>();
		for (Permissions p : permissions) {
			PermissionsJson permissionJson = new PermissionsJson();
			permissionJson.setId(p.getId());
			permissionJson.setName(p.getName());
			if (p.getPermissions() != null) {
				permissionJson.setParentId(p.getPermissions().getId());
			} else {
				permissionJson.setParentId(0);
			}

			jsonPermissions.add(permissionJson);
		}
		return jsonPermissions;
	}
	
	@RequestMapping(value="/roles/{roleId}/permission",method = RequestMethod.PUT,headers = "Accept=application/json")
	public JsonMap assignRolePermissions(@RequestBody List<Long> permissions,@PathVariable long roleId){
		JsonMap jsonMap=new JsonMap();
		long rolePermissionId=roleService.assignRolePermissions(permissions,roleId);
		if(rolePermissionId>0){
			jsonMap.setSuccessStatus();
			jsonMap.setMessages("permissionAssignedSuccessfullyMsg");
		}
		else{
			jsonMap.setFailedStatus();
			jsonMap.setMessages("failureMsg");
		}
		return jsonMap;
	}

	// @RequestMapping(value = "/roles/{roleId}/permissions", method =
	// RequestMethod.GET)
	// public JsonMap listAllRolePermissions(@PathVariable long roleId) throws
	// ParseException {
	// JsonMap jsonMap = new JsonMap();
	// List<RolePermissions> rolePermissions =
	// roleService.getRoleMembersByRoleId(roleId);
	// List<Long> jsonPermissions = new ArrayList<Long>();
	// for (RolePermissions rp : rolePermissions) {
	// jsonPermissions.add(rp.getPermissions().getId());
	// }
	// jsonMap.setPermissionId(jsonPermissions);
	// return jsonMap;
	// }

}
