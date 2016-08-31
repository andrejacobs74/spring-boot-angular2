export class User {

    private username: string = '';
    private password: string = '';

    constructor() {  }

    public getUsername() : string {
        return this.username;
    }

    public getPassword() : string {
        return this.password;
    }

}