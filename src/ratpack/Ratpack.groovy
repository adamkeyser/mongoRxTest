import ratpack.exec.Promise
import ratpack.groovy.template.MarkupTemplateModule
import ratpack.server.Service
import ratpack.server.StartEvent
import ratpack.rx.RxRatpack

import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json

ratpack {
  bindings {
    module MarkupTemplateModule
    bind MongoService
    bindInstance Service, new Service() {
      @Override
      void onStart(StartEvent event) throws Exception {
        RxRatpack.initialize()
      }
    }
  }

  handlers {
    get {
      registry.get(MongoService).find().toList().subscribe() {
        render json(it)
      }
    }

    path("works") {
      byMethod {
        get {
          Promise.of { f ->
            registry.get(MongoService).find().toList().subscribe() {
              f.success(json(it))
            }
          }.then {
            render it
          }
        }
      }
    }

    files { dir "public" }
  }
}
