DIST = gh-pages-dist
DESTINATION = $(DIST)/slides

dist:
	mkdir -p $(DESTINATION)
	cp pdxscala-beginner-talk-slides-2015-03-17-rendered.md $(DESTINATION)/pdxscala-2015-march
	cp pdxscala-beginner-talk-slides-2015-03-17.html $(DESTINATION)/pdxscala-2015-march/index.html

push-gh-pages-dist:
	git subtree push --prefix $(DIST) origin gh-pages
