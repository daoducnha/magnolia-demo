package com.ekino.app.setup;

import info.magnolia.cms.security.Permission;
import info.magnolia.module.DefaultModuleVersionHandler;
import info.magnolia.module.InstallContext;
import info.magnolia.module.delta.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is optional and lets you manage the versions of your module,
 * by registering "deltas" to maintain the module's configuration, or other type of content.
 * If you don't need this, simply remove the reference to this class in the module descriptor xml.
 *
 * @see info.magnolia.module.DefaultModuleVersionHandler
 * @see info.magnolia.module.ModuleVersionHandler
 * @see info.magnolia.module.delta.Task
 */
public class FirstAppVersionHandler extends DefaultModuleVersionHandler {
    public final static String DEMO_REST_ROLE = "demo-rest-role";

    public FirstAppVersionHandler() {
        register(DeltaBuilder.update("1.0", "")
                .addTask(new BootstrapSingleModuleResource("Bootstrap file userroles.demo-rest-role.xml", "", "userroles.demo-rest-role.xml"))
                .addTask(new AddRoleToUserTask("Add role 'demo-rest-role' to user 'superuser'", "superuser", DEMO_REST_ROLE))

        );
    }
    @Override
    protected List<Task> getExtraInstallTasks(InstallContext installContext) {
        List<Task> list = new ArrayList<>();

        // Add role 'demo-rest-role' to superuser
        list.add(new AddRoleToUserTask("Add role 'demo-rest-role' to user 'superuser'", "superuser", DEMO_REST_ROLE));

        // Deny web access to '/.rest' in role 'demo-rest-role'
        // Allow access to the Swagger tool. This is only required if you have the magnolia-rest-tools module installed
        // which is used for testing and development
        list.add(new ArrayDelegateTask("Update 'demo-rest-role' role",
                new AddPermissionTask("Update 'demo-rest-role' role","Denies access to the REST interfaces residing under '/.rest*' to role 'demo-rest-role'.", DEMO_REST_ROLE, "uri", "/.rest*", Permission.NONE, false),

                new AddPermissionTask("Update 'demo-rest-role' role","Allow Get & Post access to the REST interfaces residing under '/.rest/swagger*' to role 'demo-rest-role'", DEMO_REST_ROLE, "uri", "/.rest/swagger*", Permission.ALL, false)));

        return list;
    }
}
