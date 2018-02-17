import {Injectable} from '@angular/core';
import { Widget } from '../models/widget.model.client';

@Injectable()
export  class WidgetService {

  // constructor(_id:String, type:String, pageId:String, size= '1', text = 'text', url = 'url', width = '100%')
  widgets: Widget[] = [
    new Widget('123', 'HEADER', '321', '2', 'GOP Releases Formerly Classified Memo Critical Of FBI' ),
    new Widget('234', 'HEADER', '321', '4', 'It hints at a new GOP target: deputy attorney general' ),
    new Widget('345', 'IMAGE', '321', '2', 'text', '100%',
      'https://media.fox5dc.com/media.fox5dc.com/photo/2018/02/01/trump_classified_1517500733623_4880181_ver1.0_640_360.jpg'),
    new Widget('456', 'HTML', '321', '2', '<p>blalbla</p>' ),
    new Widget('567', 'HEADER', '321', '4', 'Memo asserts bias on part of FBI investigation in Russia probe'),
    new Widget('678', 'YOUTUBE', '321', '2', 'text', '100%', 'https://www.youtube.com/embed/I84wnvEqGXc' ),
  ];

  findWidgetByPage(pageId: String) {
    const resultSet = [];
    for ( const i in this.widgets) {
      if (this.widgets[i].pageId === pageId) {
        resultSet.push(this.widgets[i]);
      }
    }
    return resultSet;
  }

  findWidgetById(widgetId: String) {
    let widget: Widget;
    for ( const i in this.widgets) {
      if (this.widgets[i]._id === widgetId) {
        widget = this.widgets[i];
      }
    }
    return widget;
  }

  createWidget(pageId, widget) {
    this.widgets.push(widget);
  }

  deleteWidget(widget) {
    for (const i in this.widgets) {
      if (this.widgets[i] === widget) {
        const j = +i;
        this.widgets.splice(j, 1);
      }
    }
  }

  updateWidget(widgetId, widget) {
    for ( const i in this.widgets ) {
      if ( this.widgets[i]._id === widgetId ) {
        switch (widget.widgetType) {
          case 'HEADER':
            this.widgets[i].text = widget.text;
            this.widgets[i].size = widget.size;
            return true;

          case 'IMAGE':
            this.widgets[i].text = widget.text;
            this.widgets[i].url = widget.url;
            this.widgets[i].width = widget.width;
            return true;

          case 'YOUTUBE':
            this.widgets[i].text = widget.text;
            this.widgets[i].url = widget.url;
            this.widgets[i].width = widget.width;
            return true;
        }

      }
    }
    return false;
  }

  deleteWidgetbyID(widgetID: String) {
    for (const i in this.widgets) {
      if (this.widgets[i]._id === widgetID) {
        const j = +i;
        this.widgets.splice(j, 1);
      }
    }
  }
}
