import {Component, OnInit, ViewChild} from '@angular/core';
import {NgForm} from '@angular/forms';
import {UserService} from '../../../services/user.service.client';
import {Router} from '@angular/router';
import {User} from '../../../models/user.model.client';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  @ViewChild('f') registerForm: NgForm;
  username: String;
  password: String;
  verifypw: String;
  pwErrorFlag: boolean;
  pwErrorMsg = 'Password should be same';
  userErrorFlag: boolean;
  userErrorMsg = 'Already Exist this userName';
  constructor(private userService: UserService, private router: Router) { }

  register() {
    this.username = this.registerForm.value.username;
    this.password = this.registerForm.value.password;
    this.verifypw = this.registerForm.value.verifypw;
    if (this.password !== this.verifypw) {
      this.pwErrorFlag = true;
    }
    if (this.userService.findUserByCredential(this.username, this.password)) {
      this.userErrorFlag = true;
    }

    const user: User = new User(this.userService.users.length, this.username, this.password);
    this.userService.createUser(user);
    this.router.navigate(['/profile', user._id]);
  }
  login() {
    this.router.navigate(['/login']);
  }
  ngOnInit() {
  }

}
