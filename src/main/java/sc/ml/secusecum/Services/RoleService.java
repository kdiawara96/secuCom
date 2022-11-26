package sc.ml.secusecum.Services;


import sc.ml.secusecum.Modeles.Roles;

public interface RoleService {
    public Roles addRole(Roles name);

    void AddRoleToUser(String username, String name);

    public Roles findbyname(String name);
}
