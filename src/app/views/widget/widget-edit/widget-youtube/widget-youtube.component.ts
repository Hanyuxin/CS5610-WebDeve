import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {WidgetService} from '../../../../services/widget.service.client';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-widget-youtube',
  templateUrl: './widget-youtube.component.html',
  styleUrls: ['./widget-youtube.component.css']
})
export class WidgetYoutubeComponent implements OnInit {

  @ViewChild('f') youtubeForm: NgForm;
  pageID: String;
  wgid: String;
  width: String;
  name: String;
  text: String;
  url: String;
  constructor(private activatedRoute: ActivatedRoute, private widgetService: WidgetService) { }

  delete () {
    const widget = this.widgetService.deleteWidgetbyID(this.wgid);
  }
  ngOnInit() {
    this.activatedRoute.params.subscribe(
      (params: any) => {
        console.log(params['pid']);
        this.pageID = params['pid'];
      }
    );

    this.activatedRoute.params.subscribe(params => {
      console.log(params['wgid']);
      this.wgid = params['wgid'];
    });
  }

}
