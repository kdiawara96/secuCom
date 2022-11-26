package sc.ml.secusecum.Services;


import sc.ml.secusecum.Modeles.Roles;

public interface RoleService {
    public Roles addRole(Roles name);

    void AddRoleToUser(String username, Roles name);
    public Roles findbyname(Roles name);
}
