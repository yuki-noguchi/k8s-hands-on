<script lang="ts">
  import { onMount } from "svelte";

  const baseUrl = "http://api.michiko.praise.com/praise/";

  let mihikoImg = "images/michiko.jpg";
  let name = "";
  let praised = "";
  let showAddModal = false;
  let addingPraiseWord = "";
  let praiseWords: string[] = [];

  const addPraiseWord = async () => {
    if (!addingPraiseWord) {
      return;
    }
    await fetch(baseUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ word: addingPraiseWord }),
    });
    fetchPraiseWords();
    showAddModal = false;
    addingPraiseWord = "";
  };

  const randomPraise = async () => {
    if (!name) {
      return;
    }
    const res = await fetch(baseUrl + "random");
    const json = await res.json();
    praised = json.word;
  };

  const fetchPraiseWords = async () => {
    const res = await fetch(baseUrl + "list");
    const json: Array<any> = await res.json();
    praiseWords = json.map((item) => item.word);
  };

  const handleNameInputPress = async (e: KeyboardEvent) => {
    if (e.key === "Enter") {
      await randomPraise();
    }
  };

  const handleNameSubmit = async () => {
    await randomPraise();
  };

  const handleAddWordInputPress = async (e: KeyboardEvent) => {
    if (e.key === "Enter") {
      await addPraiseWord();
    }
  };

  const handleAddWordSubmit = async () => {
    await addPraiseWord();
  };

  const handleAddButtonClicked = () => {
    showAddModal = true;
  };

  onMount(async () => {
    fetchPraiseWords();
  });
</script>

<main>
  <div class="main-container">
    <h1>みちこがあなたを褒め殺しにするよ！</h1>
    <div class="main-image-container">
      <img src={mihikoImg} alt="みちこさま" />
    </div>
    <div class="input-container">
      <input
        bind:value={name}
        placeholder="名前を入力してね"
        on:keypress={handleNameInputPress}
      />
      <button on:click={handleNameSubmit}>褒められる！</button>
    </div>
    <div class="praised">
      {#if praised}
        <p>こんなふうに褒めてくれたよ！</p>
        <p>{praised}{name}！</p>
      {/if}
    </div>
    <button on:click={handleAddButtonClicked}>褒め言葉を追加する</button>
    <div>
      <h3 style="text-align:center;">登録済の褒め言葉一覧</h3>
      {#if !praiseWords.length}
        <p class="warn">
          まだ褒め言葉が登録されていなので、自分で登録しましょう！
        </p>
      {/if}
      <ul>
        {#each praiseWords as registerdWord}
          <li>{registerdWord}</li>
        {/each}
      </ul>
    </div>
  </div>
</main>
{#if showAddModal}
  <div class="overlay" on:click={() => (showAddModal = false)} />
  <div class="modal">
    <div class="modal-content" />
    <div class="input-container">
      <input
        bind:value={addingPraiseWord}
        placeholder="褒め言葉を入力してね"
        on:keypress={handleAddWordInputPress}
      />
      <button on:click={handleAddWordSubmit}>追加する！</button>
    </div>
  </div>
{/if}

<style>
  .main-container {
    display: flex;
    flex-direction: column;
    height: 100vh;
    align-items: center;
    justify-content: center;
  }

  .main-image-container {
    padding: 10px;
  }

  .input-container {
    padding: 10px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    height: 50px;
  }

  img {
    border-radius: 50%;
    width: 180px;
    height: 180px;
  }

  .praised {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 10px;
  }

  .overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.6);
    backdrop-filter: blur(3px);
    z-index: 5;
  }

  .modal {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: white;
    border-radius: 5px;
    box-shadow: 0 10px 10px rgba(0, 0, 0, 0.3);
    z-index: 10;
    width: 30%;
    padding: 10px;
  }

  .warn {
    color: red;
  }
</style>
