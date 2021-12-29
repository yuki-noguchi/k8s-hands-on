<script lang="ts">
  let mihikoImg = "images/michiko.jpg";
  let name: string;

  const handleKeyDown = (event: CustomEvent | KeyboardEvent) => {
    event = event as KeyboardEvent;
    if (event.key === "Enter") {
      // post
    }
  };
</script>

<main>
  <img src={mihikoImg} alt="みちこさま" />
</main>

<style>
  main {
    text-align: center;
    padding: 1em;
    max-width: 240px;
    margin: 0 auto;
  }

  img {
    border-radius: 50%; /* 角丸半径を50%にする(=円形にする) */
    width: 180px; /* ※縦横を同値に */
    height: 180px; /* ※縦横を同値に */
  }

  @media (min-width: 640px) {
    main {
      max-width: none;
    }
  }
</style>
